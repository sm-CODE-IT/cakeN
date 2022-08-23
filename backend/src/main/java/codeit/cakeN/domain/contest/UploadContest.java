package codeit.cakeN.domain.contest;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class UploadContest {

    private String uploadFileName;   // 유저가 업로드한 파일명 -> 서로 다른 유저가 같은 파일명으로 업로드 시 충돌 발생 방지
    private String storeFileName;    // 서버 내부에서 관리하는 파일명

    public UploadContest(String originalFileName, String storeFileName) {
        this.uploadFileName = originalFileName;
        this.storeFileName = storeFileName;
    }
}
