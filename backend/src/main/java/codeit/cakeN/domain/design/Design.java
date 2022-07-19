package codeit.cakeN.domain.design;

import codeit.cakeN.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter  @Setter
@Table(name = "DESIGN")
public class Design extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long designId;

    @Column
    private String file;   // 내보내기

    @Column
    private String cakeShape;    // 케이크 모양

    @Column
    private String cakeColor;    // 케이크 색상
    
    @Column
    private String image;   // 이미지
    
    @Column
    private String letter;   // 레터링 문구
    
    @Column
    private String letterColor;  // 레터링 색상

//    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
//    @JoinColumn(name = "user_id", updatable = false)
//    private User user_id;

    @Column
    private String likeLetter;


}
