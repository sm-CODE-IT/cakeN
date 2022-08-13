package codeit.cakeN.domain.letter;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LETTER")
public class Letter {

    @Id
    @GeneratedValue
    @Column(name = "letter_id", unique = true, nullable = false)
    private Long letter_id;

    @Column(length = 100, nullable = false)
    private String letter;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    private LocalDateTime updated_at;

    @Builder
    public Letter(Long letter_id, String letter) {
        this.letter_id = letter_id;
        this.letter = letter;
    }

}

