package codeit.cakeN.domain.design;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.web.design.dto.DesignRequestDto;
import codeit.cakeN.web.design.dto.DesignUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter  @Setter
@Table(name = "DESIGN")
public class Design extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long designId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String file;   // 내보내기

    @Column
    private String cakeShape;    // 케이크 모양

    @Column
    private String cakeColor;    // 케이크 색상
    
    @Column
    private String image;   // 이미지
    
    @Column
    private String letter;   // 레터링 문구
    
    @Column
    private String letterColor;  // 레터링 색상

//    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
//    @JoinColumn(name = "user_id", updatable = false)
//    private User user_id;

    @Column
    private String likeLetter;


    // 연관관계 메서드
    public void confirmWriter(User user) {
        this.user = user;
        user.addDesign(this);
    }

    @Builder
    public Design(User user, String file, String cakeShape, String cakeColor, String image, String letter, String letterColor) {
        this.user = user;
        this.file = file;
        this.cakeShape = cakeShape;
        this.cakeColor = cakeColor;
        this.image = image;
        this.letter = letter;
        this.letterColor = letterColor;
    }

    // API 응답 시 사용
    public Design(DesignRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.file = requestDto.getFile();
        this.cakeShape = requestDto.getCakeShape();
        this.cakeColor = requestDto.getCakeColor();
        this.image = requestDto.getImage();
        this.letter = requestDto.getLetter();
        this.letterColor = requestDto.getLetterColor();
    }

    public Design update(DesignUpdateDto requestDto) {
        this.file = requestDto.getFile();
        this.cakeShape = requestDto.getCakeShape();
        this.cakeColor = requestDto.getCakeColor();
        this.image = requestDto.getImage();
        this.letter = requestDto.getLetter();
        this.letterColor = requestDto.getLetterColor();

        return this;
    }
}
