package codeit.cakeN.NewLetter.domain;

import codeit.cakeN.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "letter_id")
    @ManyToOne
    private NewLetter newletter;

    @JoinColumn(name = "user_id")   // userId??
    @ManyToOne
    private User user;

    @Builder
    public Heart(NewLetter newletter, User user) {
        this.newletter = newletter;
        this.user = user;
    }




}
