package codeit.cakeN.config.auth;

        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Login 성공 : " + authentication.getName() + "(AuthSuccessHandler 호출)");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        response.getWriter().write("success");
    }
}

