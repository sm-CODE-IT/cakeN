/*
package codeit.cakeN.config.auth.jwt;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        // 유효한 자격증명을 제공하지 않고 접근하고자 할 때 401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
*/
