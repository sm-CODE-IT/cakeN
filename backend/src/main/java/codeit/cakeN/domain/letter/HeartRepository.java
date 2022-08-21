package codeit.cakeN.domain.letter;

import codeit.cakeN.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findHeartByUserAndLetter(User user, Letter letter);
}
