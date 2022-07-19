package codeit.cakeN.domain.design;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @Column(name="created_at")
    @CreatedDate    // Entity가 생성되어 저장될 때 시간 자동 저장 -> 실질적으로 필요한 건 이 데이터
    private String createdAt;   

    @Column(name="modified_at")
    @LastModifiedDate    // 조회한 Entity의 값을 변경할 때 시간 자동 저장
    private String modifiedAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.modifiedAt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedAt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
