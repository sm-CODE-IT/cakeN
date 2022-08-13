package codeit.cakeN.web.user.dto;

import codeit.cakeN.domain.user.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserLoginRequestDto {

    @NotEmpty(message = "아이디는 필수 입력값입니다.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    private Role role = Role.USER;

}