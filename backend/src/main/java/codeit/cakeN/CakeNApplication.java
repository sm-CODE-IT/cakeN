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
}
