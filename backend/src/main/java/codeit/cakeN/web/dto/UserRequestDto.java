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
    private String intro;
    private String image;

    @NotBlank
    private String nickname;

    private Role role;

    @Builder
    public UserRequestDto(String email, String pw, String intro, String image, String nickname, Role role) {
        this.email = email;
        this.pw = pw;
        this.intro = intro;
        this.image = image;
        this.nickname = nickname;
        this.role = role;
    }


    public User toEntity() {
        return User.builder()
                .email(email)
                .pw(pw)
                .intro(intro)
                .image(image)
                .nickname(nickname)
                .role(Role.USER)
                .build();
    }
}
