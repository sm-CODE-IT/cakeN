package codeit.cakeN.config.auth;

import codeit.cakeN.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                        ,"/error"
                );
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);   // 서버 실행 시 사용자 인증 과정 생략
        http
                .csrf().disable()   // csrf 비활성화
                .cors().disable()   // cors 비활성화
                .formLogin().disable()   // 기본 로그인 페이지 생략
                .headers().frameOptions().disable()

                .and()    // 페이지 권한 설정
                .authorizeRequests()  // url 별 권한 관리 설정 by antMatcher()
                .antMatchers("/", "/users/**", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/api/**").permitAll()   // 해당 경로는 모든 유저에 접근 권한 부여
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/users/mypage/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())   // 해당 주소는 USER 권한을 가진 사람만 열람 가능
                .anyRequest().permitAll()   // 설정된 값들 외의 URL 들은 모두 인증된 사용자(=로그인 O)들에게만 허용

                .and()    // 로그인 설정
                .formLogin()
                .loginPage("/users/login")
                .loginProcessingUrl("/users/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/users/login")
                .failureHandler(new AuthFailHandler())
                .successHandler(new AuthSuccessHandler())
                .permitAll()

                .and()    // 로그아웃 설정
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")

                .and()    // 소셜 로그인 설정
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

//        http.addFilterAfter(jwtAuthenticationFilter, LogoutFilter.class);



        /*
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))    // 로그아웃 url
                .logoutSuccessUrl("/users/login")   // 로그아웃 성공 시 진입점 지정
                .invalidateHttpSession(true)    // 인증정보를 지우고 세션 무효화
                .deleteCookies("JSESSIONID")   // JSESSIONID 쿠키 삭제
                .permitAll()*/





        // TODO 예외처리 핸들링 (404 페이지)

    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthSuccessHandler();
    }
}
