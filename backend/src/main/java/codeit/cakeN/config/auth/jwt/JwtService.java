package codeit.cakeN.config.auth.jwt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public interface JwtService {

    String createAccessToken(String username);
    String createRefreshToken();

    void updateRefreshToken(String username, String refreshToken);

    void destroyRefreshToken(String username);

    void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) throws IOException;
    void sendAccessToken(HttpServletResponse response, String accessToken);

    Optional<String> extractAccessToken(HttpServletRequest request) throws IOException, ServletException;
    Optional<String> extractRefreshToken(HttpServletRequest request) throws IOException, ServletException;
    Optional<String> extractUsername(String accessToken);

    void setAccessTokenHeader(HttpServletResponse response, String accessToken);
    void setRefreshTokenHeader(HttpServletResponse response, String refreshToken);

    boolean isTokenValid(String token);  // 토큰의 유효성 검사
}
