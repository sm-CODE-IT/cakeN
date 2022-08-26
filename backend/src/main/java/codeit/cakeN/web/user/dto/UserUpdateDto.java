package codeit.cakeN.web.user.dto;


import codeit.cakeN.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Data
public class UserUpdateDto {

    private Long id;
/*    private String pw;
    private String pwConfirm;*/
    @NotBlank
    private String nickname;
    private String intro;
    private String image;

    @Builder
    public UserUpdateDto(User user) {
        this.id = user.getUserId();
//        this.pw = user.getPw();
        this.nickname = user.getNickname();
        this.intro = user.getIntro();
        this.image = user.getImage();
    }

    /*public User toEntity() {
        return User.builder()
                .pw(pw)
                .intro(intro)
                .image(image)
                .nickname(nickname)
                .build();
    }*/
}
