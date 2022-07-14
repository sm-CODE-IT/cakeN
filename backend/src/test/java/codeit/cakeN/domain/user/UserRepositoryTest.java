package codeit.cakeN.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }
    
    @Test
    public void 회원조회() {
        //given
        User user = new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun"
        );

        /*userRepository.save(User.builder()
                .email("jun020216@naver.com")
                .pw("****")
                .intro("안녕")
                .image("11.jpg")
                .nickname("jun")
                .build());*/
        userRepository.save(user);

        //when

        //then

        // ============== 데이터 조회 ================
        System.out.println("=== 전체 데이터 조회 ===");
        List<User> userList = userRepository.findAll();

        for (int i=0; i<userList.size(); i++) {
            User user1 = userList.get(i);
            System.out.println("ID = " + user1.getId());
            System.out.println("이름 = " + user1.getNickname());
            System.out.println("이메일 = " + user1.getEmail());
        }

        // ============== 데이터 1개 조회 ================
        User u = userRepository.findById(userList.get(0).getId()).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );

        System.out.println("ID = " + u.getId());
        System.out.println("이름 = " + u.getNickname());
        System.out.println("이메일 = " + u.getEmail());


        assertThat(u.getNickname()).isEqualTo("jun");
    }
    /*	@Bean
	public CommandLineRunner demo(UserRepository userRepository, UserService userService) {
		return (args) -> {
			// ============== 데이터 추가 ================
			User user = new User("jun020216@naver.com", "****", "안녕", "11.jpg", "jun");
			userRepository.save(user);

			// ============== 데이터 조회 ================
			System.out.println("=== 전체 데이터 조회 ===");
			List<User> userList = userRepository.findAll();

			for (int i=0; i<userList.size(); i++) {
				User user1 = userList.get(i);
				System.out.println("ID = " + user1.getId());
				System.out.println("이름 = " + user1.getNickname());
				System.out.println("이메일 = " + user1.getEmail());
			}

			// ============== 데이터 업데이트 ================
			UserRequestDto requestDto = new UserRequestDto("jun020216@naver.com", "****", "안녕", "11.jpg", "jun");
			userService.update(1L, requestDto);
			System.out.println("=== 데이터 업데이트 완료 ===");

			// ============== 데이터 1개 조회 ================
			User u = userRepository.findById(1L).orElseThrow(
					() -> new NullPointerException("아이디가 존재하지 않습니다.")
			);

			System.out.println("ID = " + u.getId());
			System.out.println("이름 = " + u.getNickname());
			System.out.println("이메일 = " + u.getEmail());

		};
	}*/

}