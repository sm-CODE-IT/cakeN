package codeit.cakeN.web.dto;

import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.service.user.UserService;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// 데이터 수정 전 User 객체를 임시 저장
@Getter
@NoArgsConstructor
@Data
public class UserRequestDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String pw;
    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String pwConfirm;
    private String intro;
    private String image;

    @NotBlank
    private String nickname;

    private Role role;

    @Builder
    public UserRequestDto(User user) {
        this.email = user.getEmail();
        this.pw = user.getPw();
        this.intro = user.getIntro();
        this.image = user.getIntro();
        this.nickname = user.getNickname();
        this.role = user.getRole();
    }


    public User toEntity() {
        return User.builder()
                .email(email)
                .pw(pw)
                .intro(intro)
                .image(image)
                .nickname(nickname)
                .role(Role.USER)   // 회원가입 시 USER로 권한 부여
                .build();
    }
}
