package codeit.cakeN.NewLetter.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class NewLetter {

    @Id
    @GeneratedValue
    @Nullable
    private int letter_id;      // 게시글의 번호

    // 작성자 어떻게 데리고 오지? 현재 로그인 한 사람
    // user_id

    private String letter;      // 레터의 내용

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    private int tag;
/*
    @OneToMany(mappedBy = "newletter")
    private List<Heart> heartList;
 */

    // ====================

    private Integer heartCount;

    private Boolean isHeart = false;


}
