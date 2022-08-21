package codeit.cakeN.domain.contest;

import codeit.cakeN.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scrap")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    public static Scrap toContestScrap(User user, Contest contest) {
        Scrap contestScrap = new Scrap();
        contestScrap.setUser(user);
        contestScrap.setContest(contest);

        return contestScrap;
    }
}
