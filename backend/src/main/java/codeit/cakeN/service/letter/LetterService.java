package codeit.cakeN.service.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import codeit.cakeN.exception.letter.LetterException;
import codeit.cakeN.exception.letter.LetterExceptionType;
import codeit.cakeN.web.letter.dto.LetterRequestDto;
import codeit.cakeN.web.letter.dto.LetterUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public void register(LetterRequestDto letterRequestDto) {
        letterRepository.save(letterRequestDto.toEntity());
    }

    public List<Letter> list() {
        return letterRepository.findAll(Sort.by(Sort.Direction.DESC, "letterId"));
    }

    public Letter detail(Long letterId) {
        return letterRepository.findById(letterId).orElse(null);
    }

    public void update(Long id, LetterUpdateDto letterUpdateDto) {
        Letter letter = letterRepository.findById(id).orElseThrow(
                () -> new LetterException(LetterExceptionType.NOT_FOUND_LETTER)
        );
        letter.update(letterUpdateDto);
    }

    public void delete(Long letterId) {
        letterRepository.deleteById(letterId);
    }
}
