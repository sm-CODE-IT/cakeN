package codeit.cakeN.NewLetter.controller;

import codeit.cakeN.NewLetter.domain.NewLetter;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
public class NewLetterDto {

    @NonNull
    private Page<NewLetter> newletter;
}
