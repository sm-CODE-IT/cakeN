package codeit.cakeN.web.letter.dto;

import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import codeit.cakeN.domain.user.UserRepository;
import lombok.*;

@Data
@NoArgsConstructor
public class HeartDto {

    private Long userId;
    private Long letterId;

    private UserRepository userRepository;
    private LetterRepository letterRepository;

    @Builder
    public HeartDto(Heart heart) {
        this.userId = heart.getUser().getUserId();
        this.letterId = heart.getLetter().getLetterId();
    }

    public Heart toEntity() {
        return Heart.builder()
                .user(userRepository.findById(userId).orElseThrow(null))
                .letter(letterRepository.findById(letterId).orElseThrow(null))
                .build();
    }

}