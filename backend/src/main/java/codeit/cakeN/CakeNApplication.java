package codeit.cakeN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing   // JpaAuditing 활성화 -> 생성/수정일자 자동 등록
@SpringBootApplication
public class CakeNApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakeNApplication.class, args);
	}
}
