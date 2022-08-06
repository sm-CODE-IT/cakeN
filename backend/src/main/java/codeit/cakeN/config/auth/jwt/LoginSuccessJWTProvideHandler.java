package codeit.cakeN.config.auth.jwt;

import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class LoginSuccessJWTProvideHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = extractUsername(authentication);
        String accessToken = jwtService.createAccessToken(username);
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);

        userRepository.findByEmail(username).ifPresent(
                user -> user.updateRefreshToken(refreshToken)
        );

        System.out.println("로그인에 성공하였습니다. username: " + username);
        System.out.println("AccessToken을 발급합니다. AccessToken: " + accessToken);
        System.out.println("RefreshToken을 발급합니다. RefreshToken: " + refreshToken);

        System.out.println("Login 성공 : " + authentication.getName() + "(AuthSuccessHandler 호출)");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        response.sendRedirect("/");
    }

    private String extractUsername(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
