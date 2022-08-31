package codeit.cakeN.web;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// 프로필 이미지 변경 폼
@Data
public class FileForm {
    
    private Long fileId;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}
