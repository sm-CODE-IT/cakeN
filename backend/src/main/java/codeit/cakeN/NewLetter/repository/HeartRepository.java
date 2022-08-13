package codeit.cakeN.NewLetter.repository;

import codeit.cakeN.NewLetter.domain.Heart;
import codeit.cakeN.NewLetter.domain.NewLetter;
import codeit.cakeN.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findHeartByNewletterAndUser(NewLetter newletter, User user);

    /*
    @Modifying
    @Query(value = "INSERT INTO heart(letter_id, user_id) VALUES()")

     */
}
