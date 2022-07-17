package codeit.cakeN.domain.user;

import codeit.cakeN.web.dto.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "USER")
public class User extends Timestamped implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String pw;

    @Column
    private String intro;

    @Column(nullable = true)
    private String image;

    @Column(length = 20, nullable = false)
    private String nickname;


    @Builder
    public User(String email, String pw, String intro, String image, String nickname) {
        this.email = email;
        this.pw = pw;
        this.intro = intro;
        this.image = image;
        this.nickname = nickname;
    }

    // API 응답 시 사용
    public User(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.pw = requestDto.getPw();
        this.intro = requestDto.getIntro();
        this.image = requestDto.getImage();
        this.nickname = requestDto.getNickname();
    }

    // 유저 정보 수정 시
    public void update(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.pw = requestDto.getPw();
        this.intro = requestDto.getIntro();
        this.image = requestDto.getImage();
        this.nickname = requestDto.getNickname();
    }

    // 계정이 가진 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "계정별 등록 권한";
        });

        return collectors;
    }

    @Override
    public String getPassword() {
        return this.pw;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
