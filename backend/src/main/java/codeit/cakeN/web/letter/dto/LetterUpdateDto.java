package codeit.cakeN.web.letter.dto;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class LetterUpdateDto {

    private Long id;
    private String content;
    private Tag tag;

    @Builder
    public LetterUpdateDto(Letter letter) {
        this.id = letter.getLetterId();
        this.content = letter.getContent();
        this.tag = letter.getTag();
    }
}
