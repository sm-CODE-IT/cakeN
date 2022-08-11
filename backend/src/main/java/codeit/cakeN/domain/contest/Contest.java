package codeit.cakeN.domain.contest;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
// @Data
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer postId;

    // 현재 로그인 된
    @Column
    private Integer userId;

    @Column
    private String postImage;

    @Column(length = 20)
    private String postName;

    @Column(length = 200, nullable = false)
    private String postDes;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column
    private LocalDateTime updatedAt;

}
