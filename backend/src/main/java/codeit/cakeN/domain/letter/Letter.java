package codeit.cakeN.domain.letter;

import codeit.cakeN.web.letter.dto.LetterUpdateDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "LETTER")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id", unique = true, nullable = true)
    private Long letterId;      // 게시글의 번호

    // 작성자 어떻게 데리고 오지? 현재 로그인 한 사람
    // user_id

    @Column
    private String content;      // 레터의 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tag tag;

    @Column
    private Integer hearts = 0;   // 좋아요 수

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;


    @Builder
    public Letter(String content, Tag tag) {
        this.content = content;
        this.tag = tag;
    }

    // Letter 정보 수정 메서드
    public void update(LetterUpdateDto letter) {
        this.content = letter.getContent();
        this.tag = letter.getTag();
    }

}

