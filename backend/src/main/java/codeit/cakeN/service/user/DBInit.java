package codeit.cakeN.service.user;

import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

// DB Test를 위한 초기 실행 지정
@RequiredArgsConstructor
@Service
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();

        User admin = new User("admin@admin.com", passwordEncoder.encode("admin"), "hello-intro", "image", "JUN", Role.ADMIN);
        User guest = new User("guest@guest.com", passwordEncoder.encode("guest"), "hello-intro", "image", "JUN2", Role.GUEST);
        User user = new User("user@user.com", passwordEncoder.encode("user"), "hello-intro", "image", "user", Role.USER);

        List<User> userList = Arrays.asList(admin, guest, user);

        // save to db
        this.userRepository.saveAll(userList);
        System.out.println(this.userRepository.findAll());

    }
}
