package codeit.cakeN.service.user;

import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;


    @Test
    public void 로그인_역할_변경() {
        //given
        userRepository.save(
                new User(
                        "jun020216@naver.com",
                        "****",
                        "안녕",
                        "11.jpg",
                        "jun",
                        Role.GUEST
                )
        );

        //when
        User findUser = loginService.login("guest@guest.com", "guest");

        //then -> 로그인 시 Role: GUSET -> USER로 변환
        System.out.println(findUser.getRole());
        System.out.println(findUser.getRoleKey());
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }

}