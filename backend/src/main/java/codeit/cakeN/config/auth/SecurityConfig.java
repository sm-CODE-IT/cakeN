package codeit.cakeN.config.auth;

import codeit.cakeN.config.auth.jwt.JwtAuthenticationFilter;
import codeit.cakeN.config.auth.jwt.JwtService;
import codeit.cakeN.config.auth.jwt.LoginSuccessJWTProvideHandler;
import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CorsConfig corsConfig;

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
                .addFilter(corsConfig.corsFilter())   // cors filter 적용
                .csrf().disable()   // csrf 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
//                .cors().disable()   // cors 비활성화
                .formLogin().disable()   // 기본 로그인 페이지 생략
                .headers().frameOptions().disable()

                .and()    // 페이지 권한 설정
                .authorizeRequests()  // url 별 권한 관리 설정 by antMatcher()
                .antMatchers("/", "/users/**", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/api/**").permitAll()   // 해당 경로는 모든 유저에 접근 권한 부여
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/users/mypage/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())   // 해당 주소는 USER 권한을 가진 사람만 열람 가능
                .anyRequest().authenticated()   // 설정된 값들 외의 URL 들은 모두 인증된 사용자(=로그인 O)들에게만 허용

                .and()    // 로그인 설정
                .formLogin()
                .loginPage("/users/login")
                .loginProcessingUrl("/users/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/users/login")
                .failureHandler(failureHandler())
                .successHandler(successHandler())
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

//        http.addFilterBefore(jwtAuthenticationProcessingFilter(), jsonUsernamePasswordAuthenticationFilter.class);



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
    
    // 비밀번호 DB 저장 시 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인 성공 핸들러 - JWT 토큰 발급
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessJWTProvideHandler(jwtService, userRepository);
    }

    // 로그인 실패 핸들러
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new AuthFailHandler();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationFilter jsonUsernamePasswordLoginFilter = new JwtAuthenticationFilter(jwtService, userRepository);

        return jsonUsernamePasswordLoginFilter;
    }
}
