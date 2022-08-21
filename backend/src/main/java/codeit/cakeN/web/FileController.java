package codeit.cakeN.web;

import codeit.cakeN.domain.contest.ContestStore;
import codeit.cakeN.domain.contest.File;
import codeit.cakeN.domain.contest.FileRepository;
import codeit.cakeN.domain.contest.UploadContest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;
    private final ContestStore contestStore;

    @GetMapping("/files/new")
    public String newFile(@ModelAttribute FileForm form) {
        return "file-form";
    }

    @PostMapping("/files/new")
    public String saveFile(@ModelAttribute FileForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadContest attachFile = contestStore.storeContest(form.getAttachFile());
        List<UploadContest> storeImageFiles = contestStore.storeFiles(form.getImageFiles());

        // 데이터베이스에 저장
        File file = new File();
        file.setItemName(form.getItemName());
        file.setAttachFile(attachFile);
        file.setImageFiles(storeImageFiles);
        fileRepository.save(file);

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
        return new UrlResource("file:" + contestStore.getFullPath(filename));
    }

    @GetMapping("/attach/{fileId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long fileId) throws MalformedURLException {
        File file = fileRepository.findById(fileId);
        String storeFileName = file.getAttachFile().getStoreFileName();
        String uploadFileName = file.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + contestStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);

        // 파일명을 인코딩하여 전달 for 한글 파일명
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
