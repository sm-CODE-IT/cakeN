package codeit.cakeN.NewLetter.repository;

import codeit.cakeN.NewLetter.domain.NewLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewLetterRepository extends JpaRepository<NewLetter, Integer> {

}
