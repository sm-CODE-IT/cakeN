package codeit.cakeN.web.design.dto;

import codeit.cakeN.domain.design.Design;
import codeit.cakeN.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Data
public class DesignUpdateDto {

    private Long id;

    private String file;

    private String cakeShape;

    private String cakeColor;

    private String image;

    private String letter;

    private String letterColor;

    @Builder
    public DesignUpdateDto(Design design) {
        this.id = design.getDesignId();
        this.file = design.getFile();
        this.cakeShape = design.getCakeShape();
        this.cakeColor = design.getCakeColor();
        this.image = design.getImage();
        this.letter = design.getLetter();
        this.letterColor = design.getLetterColor();
    }
}
