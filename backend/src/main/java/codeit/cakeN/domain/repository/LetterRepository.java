package codeit.cakeN.domain.repository;

import codeit.cakeN.domain.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
