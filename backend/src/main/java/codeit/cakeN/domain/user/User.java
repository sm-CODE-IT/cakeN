package codeit.cakeN.domain.user;

import codeit.cakeN.service.user.UserService;
import codeit.cakeN.web.dto.UserRequestDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "USER")
public class User extends Timestamped implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
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
    // TODO 이메일 인증, 계정 잠김 여부 (일정 기간 이후)
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

    public User update(String image, String nickname) {
        this.image = image;
        this.nickname = nickname;

        return this;
    }


    // 사용자의 권한 판단
    public String getRoleKey() {
        return this.role.getKey();
    }



    // 개인정보 수정
    public void updatePw(PasswordEncoder passwordEncoder, String pw) {
        this.pw = passwordEncoder.encode(pw);
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateIntro(String intro) {
        this.intro = intro;
    }

    public void updateImage(String image) {
        this.image = image;
    }

    // 비밀번호 변경 (수정), 회원 탈퇴 시 비밀번호 재확인 과정에서의 일치여부 판단
    public boolean matchPw(PasswordEncoder passwordEncoder, String checkPassword) {
        return passwordEncoder.matches(checkPassword, getPw());
    }


}
