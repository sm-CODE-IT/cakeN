package codeit.cakeN.domain.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 파일 저장과 관련된 별도의 객체
@Component
public class ProfileStore {

    @Value("${codeit.cakeN.upload.path}")
    private String filePath;

    public String getFullPath(String filename) {
        return filePath + filename;
    }

    public List<UploadProfile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadProfile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeProfile(multipartFile));
            }
        }

        return storeFileResult;
    }

    public UploadProfile storeProfile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        // 서버에 저장하는 파일명 -> uuid 사용
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File((getFullPath(storeFileName))));

        return new UploadProfile(originalFileName, storeFileName);
    }

    /**
     * 서버에 저장할 파일명 생성
     * 서버 내부에서 관리하는 파일명은 유일한 이름을 생성하는 'UUID'를 사용해서 충돌하지 않도록 한다.
     * @param originalFilename
     * @return
     */
    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();   // 뒤에 확장자는 남겨두려면
        String ext = extractExt(originalFilename);

        return uuid + "." + ext;
    }

    /**
     * 확장자 추출 메서드
     * 확장자를 별도로 추출해서 서버 내부에서 관리하는 파일명에도 붙여준다. (UUID + "." + 확장자)
     * @param originalFilename
     * @return
     */
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");

        return originalFilename.substring(pos + 1);
    }
}
