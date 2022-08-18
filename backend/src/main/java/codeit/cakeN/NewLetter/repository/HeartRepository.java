package codeit.cakeN.NewLetter.repository;

import codeit.cakeN.NewLetter.domain.Heart;
import codeit.cakeN.NewLetter.domain.NewLetter;
import codeit.cakeN.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    // Heart findHeartByNewletterAndUser(NewLetter newletter, User user);


    Optional<Heart> findHeartByUserAndCampaignId(User user, String campaignId);
    Optional<List<Heart>> findHeartByCampaignId(String userId);
    /*
    @Modifying
    @Query(value = "INSERT INTO heart(letter_id, user_id) VALUES user()")
    */

}
