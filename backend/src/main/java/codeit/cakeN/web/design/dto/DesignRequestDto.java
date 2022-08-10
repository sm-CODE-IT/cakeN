package codeit.cakeN.web.design.dto;

import codeit.cakeN.domain.design.*;
import codeit.cakeN.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@Data
public class DesignRequestDto {

    private Long id;

    private String file;

//    @NotBlank(message = "케이크 모양 선택은 필수입니다.")
    private CakeShape cakeShape;

    @NotNull(message = "케이크 패턴 선택은 필수입니다.")
    private CakePattern cakePattern;

    @NotBlank(message = "기본 색상을 하얀색입니다.")
    private String cakeColor;

    private String cakeColor2;

    private String image;

    private String letter;

    private String letterColor;

    private LetterLocation letterLocation;

    private LetterSize letterSize;

    private User user;

    @Builder
    public DesignRequestDto(Design design) {
        this.id = design.getDesignId();
        this.file = design.getFile();
        this.cakeShape = design.getCakeShape();
        this.cakePattern = design.getCakePattern();
        this.cakeColor = design.getCakeColor();
        this.cakeColor2 = design.getCakeColor2();
        this.image = design.getImage();
        this.letter = design.getLetter();
        this.letterColor = design.getLetterColor();
        this.letterLocation = design.getLetterLocation();
        this.letterSize = design.getLetterSize();
        this.user = design.getUser();
    }

    public Design toEntity() {
        return Design.builder()
                .file(file)
                .cakeShape(cakeShape)
                .cakePattern(cakePattern)
                .cakeColor(cakeColor)
                .cakeColor2(cakeColor2)
                .image(image)
                .letter(letter)
                .letterColor(letterColor)
                .letterLocation(letterLocation)
                .letterSize(letterSize)
                .user(user)
                .build();
    }
}
