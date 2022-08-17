package codeit.cakeN.web;

import codeit.cakeN.domain.user.*;
import codeit.cakeN.domain.user.profileImg.File;
import codeit.cakeN.domain.user.profileImg.FileRepository;
import codeit.cakeN.domain.user.profileImg.ProfileStore;
import codeit.cakeN.domain.user.profileImg.UploadProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;
    private final ProfileStore profileStore;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @GetMapping("/files/new")
    public String newFile(@ModelAttribute FileForm form) {
        return "file-form";
    }

    @PostMapping("/files/new")
    public String saveFile(@ModelAttribute FileForm form, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User formUser) throws IOException {
        UploadProfile attachFile = profileStore.storeProfile(form.getAttachFile());
        List<UploadProfile> storeImageFiles = profileStore.storeFiles(form.getImageFiles());

        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);


        // 데이터베이스에 저장
        File file = new File();
        file.setAttachFile(attachFile);
        file.setImageFiles(storeImageFiles);

        user.setImage(user.fileToString(file));

        fileRepository.save(user, file);


        redirectAttributes.addAttribute("fileId", file.getId());

        return "redirect:/files/{fileId}";
    }

    @GetMapping("/files/{id}")
    public String files(@PathVariable Long id, Model model) {
        File file = fileRepository.findById(id);
        model.addAttribute("file", file);
        return "file-view";
    }
    
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        // file:/Users/ ... .png
        return new UrlResource("file:" + profileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{fileId}")
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
