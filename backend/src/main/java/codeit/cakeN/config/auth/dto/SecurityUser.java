package codeit.cakeN.config.auth.dto;

import codeit.cakeN.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.Serializable;

@Slf4j
@Getter
@Setter
public class SecurityUser implements Serializable {   // 인증된 사용자 정보를 다루는 클래스


    private String name;
    private String email;
    private String picture;


    public SecurityUser(User user) {
        this.name = user.getNickname();
        this.email = user.getEmail();
        this.picture = user.getImage();
    }
}
