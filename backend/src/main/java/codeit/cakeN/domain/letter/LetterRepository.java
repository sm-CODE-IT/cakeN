package codeit.cakeN.domain.letter;

import codeit.cakeN.domain.letter.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
