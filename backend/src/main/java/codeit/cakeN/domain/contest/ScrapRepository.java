package codeit.cakeN.domain.contest;

import codeit.cakeN.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    Optional<Scrap> findScrapByUserAndContest(User user, Contest contest);
}
