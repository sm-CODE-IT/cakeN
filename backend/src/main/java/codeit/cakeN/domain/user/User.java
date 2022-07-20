package codeit.cakeN.domain.user;

import codeit.cakeN.service.user.UserService;
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
    private String image;  // 2개의 기본 이미지 중 택1

    @Column(length = 20, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    // Spring Security 사용자 인증 필드
    private boolean emailVerified;    // 이메일 인증 여부
    private boolean locked;    // 계정 잠김 여부


    @Builder
    public User(String email, String pw, String intro, String image, String nickname, Role role) {
        this.email = email;
        this.pw = pw;
        this.intro = intro;
        this.image = image;
        this.nickname = nickname;
        this.role = role;
    }

    // API 응답 시 사용
    public User(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.pw = requestDto.getPw();
        this.intro = requestDto.getIntro();
        this.image = requestDto.getImage();
        this.nickname = requestDto.getNickname();
        this.role = requestDto.getRole();;
    }


    // 유저 정보 수정 시
    public void update(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.pw = requestDto.getPw();
        this.intro = requestDto.getIntro();
        this.image = requestDto.getImage();
        this.nickname = requestDto.getNickname();
    }

    // 사용자의 권한 판단
    public String getRoleKey() {
        return this.role.getKey();
    }

    // 계정(해당 유저)이 가진 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "계정별 등록 권한";
        });

        return collectors;
    }

    // 비밀번호 가져오기
    @Override
    public String getPassword() {
        return this.pw;
    }

    // pk 값 가져오기
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * 계정 만료 여부
     * true : 만료 X
     * false : 만료 O
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    /**
     * 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자 활성화 여부
     * true : 활성화
     * false :
     * @return
     */
    @Override
    public boolean isEnabled() {
        return (emailVerified && !locked);
    }
}
