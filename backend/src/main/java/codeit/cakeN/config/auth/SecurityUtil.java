package codeit.cakeN.config.auth;

import codeit.cakeN.domain.user.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getLoginUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
