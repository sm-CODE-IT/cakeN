package codeit.cakeN.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="user_id", unique = true, nullable = false)
    private Long id;

//    private String email;
//    private String pw;
//    @Column(columnDefinition = "TEXT", nullable = true)
//    private String intro;
    // private Image image;

//    @Column(name="nickname")
    private String name;


}
