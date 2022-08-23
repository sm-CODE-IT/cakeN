package codeit.cakeN.domain.contest;

import codeit.cakeN.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="contest_scrap")
public class ContestScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    public static ContestScrap toContestScrap(User user, Contest contest) {
        ContestScrap contestScrap = new ContestScrap();
        contestScrap.setUser(user);
        contestScrap.setContest(contest);

        return contestScrap;
    }
}
