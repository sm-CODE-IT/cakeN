package codeit.cakeN;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CakeNApplicationTests {

	@Test
	void contextLoads() {
	}

	private final UserRepository userRepository;

	// 의존성 주입
	@Autowired
	public CakeNApplicationTests(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Test
	void 회원가입() {
		User user = new User();
		user.setName("yejun");
		userRepository.save(user);

		User findUser = userRepository.findById(user.getId()).get();
		Assertions.assertThat(user.getName()).isEqualTo(findUser.getName());
	}

}
