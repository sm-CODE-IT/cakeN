package codeit.cakeN;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.domain.user.UserRequestDto;
import codeit.cakeN.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing   // JpaAuditing 활성화 -> 생성/수정일자 자동 등록
@SpringBootApplication
public class CakeNApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakeNApplication.class, args);
	}

	@Bean
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
	}

}
