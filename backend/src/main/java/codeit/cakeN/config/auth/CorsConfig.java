package codeit.cakeN.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);    // 내 서버가 응답을 할 때, 응답해준 json을 자바스크립트에서 처리할 수 있게 할지 설정
        config.addAllowedOrigin("*");    // 모든 ip에 응답 허용
        config.addAllowedHeader("*");    // 모든 header에 응답 허용
        config.addAllowedMethod("*");    // 모든 HTTP Method에 요청 허용

        source.registerCorsConfiguration("/users/***", config);    // /users/**로 들어오는 모든 요청들이 config를 따르도록 등록

        return new CorsFilter(source);
    }
}
