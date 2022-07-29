package codeit.cakeN.dto;

import codeit.cakeN.domain.entity.Letter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LetterDto {
    private Long letter_id;
    private String letter;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Letter toEntity() {
        Letter build = Letter.builder()
                .letter_id(letter_id)
                .letter(letter)
                .build();
        return build;
    }

    @Builder
    public LetterDto(Long letter_id, String letter, LocalDateTime created_at, LocalDateTime updated_at) {
        this.letter_id = letter_id;
        this.letter = letter;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}