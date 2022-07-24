package codeit.cakeN.config.auth.dto;


import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;
    private String pw;
    private String intro;
    private String image;
    private String nickname;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String pw, String intro, String image, String nickname) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.pw = pw;
        this.intro = intro;
        this.image = image;
        this.nickname = nickname;
    }

    ///////// 소셜 로그인 구현 ////////////
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } /*else if ("google".equals(registrationId)) {
            return ofGoogle(userNameAttributeName, attributes);
        }*/

        return ofNaver(registrationId, attributes);
    }

    // Google
    private static OAuthAttributes ofGoogle(String userNameAttributes, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .email((String)attributes.get("email"))
                .pw((String)attributes.get("pw"))
                .intro((String)attributes.get("intro"))
                .image((String)attributes.get("image"))
                .nickname((String)attributes.get("nickname"))
                .build();
    }

    // Naver
    private static OAuthAttributes ofNaver(String userNameAttributes, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .email((String)attributes.get("email"))
                .pw((String)attributes.get("pw"))
                .intro((String)attributes.get("intro"))
                .image((String)attributes.get("image"))
                .nickname((String)attributes.get("nickname"))
                .build();
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
