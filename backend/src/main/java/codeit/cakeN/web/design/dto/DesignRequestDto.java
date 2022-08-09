package codeit.cakeN.web.design.dto;

import codeit.cakeN.domain.design.Design;
import codeit.cakeN.domain.user.User;
import lombok.*;


@Getter
@NoArgsConstructor
@Data
public class DesignRequestDto {

    private Long id;

    private String file;

    private String cakeShape;

    private String cakeColor;

    private String image;

    private String letter;

    private String letterColor;

    private User user;

    @Builder
    public DesignRequestDto(Design design) {
        this.id = design.getDesignId();
        this.file = design.getFile();
        this.cakeShape = design.getCakeShape();
        this.cakeColor = design.getCakeColor();
        this.image = design.getImage();
        this.letter = design.getLetter();
        this.letterColor = design.getLetterColor();
        this.user = design.getUser();
    }

    public Design toEntity() {
        return Design.builder()
                .file(file)
                .cakeShape(cakeShape)
                .cakeColor(cakeColor)
                .image(image)
                .letter(letter)
                .letterColor(letterColor)
                .user(user)
                .build();
    }
}
