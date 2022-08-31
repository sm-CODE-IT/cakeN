package codeit.cakeN.web.user.dto;

import codeit.cakeN.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Data
public class UserUpdatePwDto {

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String pw;

    @NotBlank
    private String newPw;
    @NotBlank(message = "변경 시, 비밀번호 확인은 필수 입력 값입니다.")
    private String newPwConfirm;

    @Builder
    public UserUpdatePwDto(User user) {
        this.pw = user.getPw();
    }

}
