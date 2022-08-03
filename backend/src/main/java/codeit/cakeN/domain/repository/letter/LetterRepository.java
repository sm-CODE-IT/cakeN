package codeit.cakeN.domain.repository.letter;

import codeit.cakeN.domain.entity.letter.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
