package codeit.cakeN.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 데이터 수정 전 User 객체를 임시 저장
@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String pw;
    private String intro;
    private String image;
    private String nickname;

    public UserRequestDto(String email, String pw, String intro, String image, String nickname) {
        this.email = email;
        this.pw = pw;
        this.intro = intro;
        this.image = image;
        this.nickname = nickname;
    }
}
