package codeit.cakeN.web.dto;

import javax.validation.constraints.NotBlank;

public record UserDeleteDto(@NotBlank(message="비밀번호를 입력하세요.") String checkPw) {
}
