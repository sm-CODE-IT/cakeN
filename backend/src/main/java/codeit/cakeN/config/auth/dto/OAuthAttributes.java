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
    private String picture;
    private String name;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String pw, String intro, String image, String nickname) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.pw = pw;   // 10자리의 임시 비밀번호로 지정
        this.intro = intro;
        this.picture = image;
        this.name = nickname;
    }

    ///////// 소셜 로그인 구현 ////////////
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } /*else if ("google".equals(registrationId)) {
            return ofGoogle(userNameAttributeName, attributes);
        }*/

        return ofGoogle(userNameAttributeName, attributes);
    }

    // Google
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {


        return OAuthAttributes.builder()
                .email((String)attributes.get("email"))
                .pw(getRandomPw(10))
                .intro("나만의 자기소개를 입력하세요!")
                .image((String)attributes.get("picture"))
                .nickname((String)attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // Naver
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        System.out.println("Naver email = " + response.get("email"));

        return OAuthAttributes.builder()
                .email((String) response.get("email"))
                .pw(getRandomPw(10))
                .intro("나만의 자기소개를 입력하세요!")
                .image((String) response.get("profile_image"))
                .nickname((String) response.get("name"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .pw(pw)
                .intro(intro)
                .image(picture)
                .nickname(name)
                .role(Role.USER)   // 가입 시 기본 권한 - GUEST
                .build();
    }


    // 랜덤으로 임시 비밀번호 지정 (소셜 로그인 시)
    public static String getRandomPw(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z'
        };

        int idx = 0;
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<size; i++)
        {
            idx = (int) (charSet.length * Math.random());
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
}
