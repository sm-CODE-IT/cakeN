package codeit.cakeN.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class UploadProfile {

    private String uploadFileName;   // 유저가 업로드한 파일명 -> 서로 다른 유저가 같은 파일명으로 업로드 시 충돌 발생 방지
    private String storeFileName;    // 서버 내부에서 관리하는 파일명

    public UploadProfile(String originalFileName, String storeFileName) {
        this.uploadFileName = originalFileName;
        this.storeFileName = storeFileName;
    }
}
