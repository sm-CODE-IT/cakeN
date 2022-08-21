package codeit.cakeN.test;

import codeit.cakeN.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 패스워드_암호화_테스트() {
        //given
        String rawPw = "123456789";

        //when
        String encodePw = passwordEncoder.encode(rawPw);

        //then
        assertAll(
                () -> assertNotEquals(rawPw, encodePw),
                () -> assertTrue(passwordEncoder.matches(rawPw, encodePw))
        );

    }

}
