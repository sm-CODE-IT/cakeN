package codeit.cakeN.web.dto;

import codeit.cakeN.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String pw;
    private String intro;
    private String image;
    private String nickname;

    @Builder
    public UserSaveRequestDto(String email, String pw, String intro, String image, String nickname) {
        this.email = email;
        this.pw = pw;
        this.intro = intro;
        this.image = image;
        this.nickname = nickname;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .pw(pw)
                .intro(intro)
                .image(image)
                .nickname(nickname)
                .build();
    }
}
