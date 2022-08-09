package codeit.cakeN.NewLetter.service;

import codeit.cakeN.Board.domain.Board;
import codeit.cakeN.NewLetter.domain.NewLetter;
import codeit.cakeN.NewLetter.repository.NewLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewLetterService {

    private final NewLetterRepository newletterRepository;

    public void register(NewLetter newletter) {
        newletterRepository.save(newletter);
    }

    public List<NewLetter> list() {
        return newletterRepository.findAll(Sort.by(Sort.Direction.DESC, "letter_id"));
    }

    public NewLetter detail(int letter_id) {
        return newletterRepository.findById(letter_id).orElse(null);
    }

    public void update(NewLetter newletter) {
        newletterRepository.save(newletter);
    }

    public void delete(int letter_id) {
        newletterRepository.deleteById(letter_id);
    }
}
