package codeit.cakeN.web.user;

import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.domain.user.profileImg.File;
import codeit.cakeN.domain.user.profileImg.FileRepository;
import codeit.cakeN.domain.user.profileImg.ProfileStore;
import codeit.cakeN.domain.user.profileImg.UploadProfile;
import codeit.cakeN.web.FileForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileRepository fileRepository;
    private final ProfileStore profileStore;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    /**
     * 파일 저장 처리
     * @param fileForm
     * @param formUser
     * @return
     * @throws IOException
     */
    @PostMapping("/users/files")
    public Long saveFile(FileForm fileForm, @AuthenticationPrincipal User formUser) throws IOException {
        UploadProfile attachFile = profileStore.storeProfile(fileForm.getAttachFile());
        List<UploadProfile> storeImageFiles = profileStore.storeFiles(fileForm.getImageFiles());

        File file = new File();
        file.setAttachFile(attachFile);
        file.setImageFiles(storeImageFiles);

        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);
        user.setImage(user.fileToString(file));

        fileRepository.save(user, file);

        return file.getId();
    }

    /**
     * 이미지 불러오기
     * @param id
     * @return
     */
    @GetMapping("/users/files/{id}")
    public ResponseEntity<File> showImage(@PathVariable("id") Long id) {
        File file = fileRepository.findById(id);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(file, header, HttpStatus.OK);
    }

    /**
     * 이미지 다운로드
     * @param filename
     * @return
     * @throws MalformedURLException
     */
    @ResponseBody
    @GetMapping("users/files/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        // file:/Users/ ... .png
        return new UrlResource("file:" + profileStore.getFullPath(filename));
    }

    /**
     * 첨부파일 저장
     * @param fileId
     * @return
     * @throws MalformedURLException
     */
    @GetMapping("users/attach/{fileId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long fileId) throws MalformedURLException {
        File file = fileRepository.findById(fileId);
        String storeFileName = file.getAttachFile().getStoreFileName();
        String uploadFileName = file.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + profileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);

        // 파일명을 인코딩하여 전달 for 한글 파일명
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
