package codeit.cakeN.web.letter.dto;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Data
public class LetterRequestDto {

    private Long id;

    @NotBlank
    private String content;

    private Tag tag;

    @Builder
    public LetterRequestDto(Letter letter) {
        this.id = letter.getLetterId();
        this.content = letter.getContent();
        this.tag = letter.getTag();
    }

    public Letter toEntity() {
        return Letter.builder()
                .content(content)
                .tag(tag)
                .build();
    }
}
