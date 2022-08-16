package codeit.cakeN.domain.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

// Contest 이미지 업로드용 테스트 Domain
@Data
public class File {

    private Long id;    // User Id와 동일하게 맞추기
    private UploadProfile attachFile;
    private List<UploadProfile> imageFiles;

}
