package codeit.cakeN.web.design.dto;

import codeit.cakeN.domain.design.*;
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

    private CakeShape cakeShape;

    private CakePattern cakePattern;

    private String cakeColor;

    private String cakeColor2;

    private String image;

    private String letter;

    private String letterColor;

    private LetterLocation letterLocation;

    private LetterSize letterSize;

    @Builder
    public DesignUpdateDto(Design design) {
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
    }
}
