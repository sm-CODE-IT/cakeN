package codeit.cakeN.domain.entity.contest;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer post_id;

    @Column
    private String post_image;

    @Column(length = 20)
    private String post_name;

    @Column
    private String created_at;

    @Column(length = 200, nullable = false)
    private String post_des;

    @Column
    private Integer user_id;

    @Builder
    public Contest(String post_image, String post_name, String post_des) {

    }

    public Contest() {

    }
}
