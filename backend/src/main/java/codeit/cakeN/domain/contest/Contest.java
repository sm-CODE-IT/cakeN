package codeit.cakeN.domain.contest;

import codeit.cakeN.domain.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "CONTEST")
// @Data
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long postId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;    // 작성자

    @Column
    private String postImage;

    @Column(length = 20)
    private String postName;

    @Column(length = 200, nullable = false)
    private String postDes;

    @Column
    private int scrapCount;  // 스크랩 수

    @Column
    private int viewCount;   // 조회수

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column
    private LocalDateTime updatedAt;

    // 파일 관련 처리
/*    private UploadContest attachFile;
    private List<UploadContest> imageFiles;*/

}
