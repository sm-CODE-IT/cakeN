package codeit.cakeN.service.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public void register(Letter letter) {
        letterRepository.save(letter);
    }

    public List<Letter> list() {
        return letterRepository.findAll(Sort.by(Sort.Direction.DESC, "letter_id"));
    }

    public Letter detail(Long letterId) {
        return letterRepository.findById(letterId).orElse(null);
    }

    public void update(Letter letter) {
        letterRepository.save(letter);
    }

    public void delete(Long letterId) {
        letterRepository.deleteById(letterId);
    }
}
