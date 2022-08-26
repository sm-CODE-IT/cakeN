package codeit.cakeN.domain.letter;

import codeit.cakeN.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findHeartByUserAndLetter(User user, Letter letter);
    Optional<Heart> findByUser_UserIdAndLetter_LetterId(Long userId, Long letterId);
    Optional<List<Heart>> findByUser(User user);
    void deleteByUser_UserIdAndLetter_LetterId(Long userId, Long letterId);
}
