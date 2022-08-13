package codeit.cakeN.domain.contest;

import lombok.Data;

import java.util.List;

// Contest 이미지 업로드용 테스트 Domain
@Data
public class File {

    private Long id;
    private String itemName;
    private UploadContest attachFile;
    private List<UploadContest> imageFiles;
}
