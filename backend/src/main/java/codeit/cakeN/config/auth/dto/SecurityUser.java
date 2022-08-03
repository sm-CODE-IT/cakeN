package codeit.cakeN.config.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

@Slf4j
@Getter
@Setter
public class SecurityUser extends User implements Serializable {   // 인증된 사용자 정보를 다루는 클래스

    private codeit.cakeN.domain.user.User user;

    public SecurityUser(codeit.cakeN.domain.user.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));

        log.info("SecurityUser user.email = {} ", user.getEmail());
        log.info("SecurityUser user.pw = {} ", user.getPassword());
        log.info("SecurityUser user.role = {} ", user.getRole().toString());

        this.user = user;
    }
}
