package codeit.cakeN.web;

import codeit.cakeN.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void 유저등록() throws Exception {
        //given

        //when

        //then
    }

}