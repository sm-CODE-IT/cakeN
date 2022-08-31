package codeit.cakeN.domain.design;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.web.design.dto.DesignRequestDto;
import codeit.cakeN.web.design.dto.DesignUpdateDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter  @Setter
@Builder
@Table(name = "DESIGN")
public class Design extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long designId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;    // 작성자

    @Column
    private String file;   // 내보내기

    @Column
    @Enumerated(EnumType.STRING)
    private CakeShape cakeShape;    // 케이크 모양

    @Column
    @Enumerated(EnumType.STRING)
    private CakePattern cakePattern;    // 케이크 패턴

    @Column
    private String cakeColor;    // 케이크 색상

    @Column(nullable = true)
    private String cakeColor2;   // 케이크 패턴에 따른 추가 색상

    @Column
    private String image;   // 이미지

    @Column
    private String letter;   // 레터링 문구


    @Column
    private String letterColor;  // 레터링 색상

    @Column
    @Enumerated(EnumType.STRING)
    private LetterLocation letterLocation;   // 레터링 위치

    @Column
    @Enumerated(EnumType.STRING)
    private LetterSize letterSize;    // 레터링 사이즈

    // TODO Letter 엔티티 매핑 -> 내가 스크랩한 문구 가져오기
    @Column
    private String scrapLetter;    // 내가 스크랩한 문구


    // 연관관계 메서드
    public void confirmWriter(User user) {
        this.user = user;
        user.addDesign(this);
    }



    @Builder
    public Design(User user, String file, CakeShape cakeShape, CakePattern cakePattern, String cakeColor, String cakeColor2, String image, String letter, String letterColor, LetterLocation letterLocation, LetterSize letterSize, String scrapLetter) {
        this.user = user;
        this.file = file;
        this.cakeShape = cakeShape;
        this.cakePattern = cakePattern;
        this.cakeColor = cakeColor;
        this.cakeColor2 = cakeColor2;
        this.image = image;
        this.letter = letter;
        this.letterColor = letterColor;
        this.letterLocation = letterLocation;
        this.letterSize = letterSize;
        this.scrapLetter = scrapLetter;
    }

    // API 응답 시 사용
    public Design(DesignRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.file = requestDto.getFile();
        this.cakeShape = requestDto.getCakeShape();
        this.cakePattern = requestDto.getCakePattern();
        this.cakeColor = requestDto.getCakeColor();
        this.cakeColor2 = requestDto.getCakeColor2();
        this.image = requestDto.getImage();
        this.letter = requestDto.getLetter();
        this.letterColor = requestDto.getLetterColor();
        this.letterLocation = requestDto.getLetterLocation();
        this.letterSize = requestDto.getLetterSize();
    }

    public Design update(DesignUpdateDto requestDto) {
        this.file = requestDto.getFile();
        this.cakeShape = requestDto.getCakeShape();
        this.cakePattern = requestDto.getCakePattern();
        this.cakeColor = requestDto.getCakeColor();
        this.cakeColor2 = requestDto.getCakeColor2();
        this.image = requestDto.getImage();
        this.letter = requestDto.getLetter();
        this.letterColor = requestDto.getLetterColor();
        this.letterLocation = requestDto.getLetterLocation();
        this.letterSize = requestDto.getLetterSize();

        return this;
    }
}