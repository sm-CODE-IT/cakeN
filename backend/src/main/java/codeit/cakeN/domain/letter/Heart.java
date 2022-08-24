package codeit.cakeN.domain.letter;

import codeit.cakeN.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "heart")
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_id")
    private Letter letter;

    public static Heart toLetterHeart(User user, Letter letter) {
        Heart letterHeart = new Heart();
        letterHeart.setUser(user);
        letterHeart.setLetter(letter);
//        user.getHeartLetterList().add(letterHeart);
        return letterHeart;
    }

    /*public void heartUser(User user) {
        this.user = user;
        user.addHeartLetter(this);
    }*/
}