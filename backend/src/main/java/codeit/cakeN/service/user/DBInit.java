package codeit.cakeN.service.user;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import codeit.cakeN.domain.letter.Tag;
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

    private final LetterRepository letterRepository;


    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();
        this.letterRepository.deleteAll();


        User admin = new User("admin@admin.com", passwordEncoder.encode("admin"), "hello-intro", "image", "JUN", Role.ADMIN);
        User guest = new User("guest@guest.com", passwordEncoder.encode("guest"), "hello-intro", "image", "JUN2", Role.GUEST);
        User user = new User("user@user.com", passwordEncoder.encode("user"), "hello-intro", "image", "user", Role.USER);

        List<User> userList = Arrays.asList(admin, guest, user);

        // save to db
        this.userRepository.saveAll(userList);
        System.out.println(this.userRepository.findAll());


        Letter birthday = new Letter("생일 축하해요", Tag.BIRTHDAY);
        Letter birthday2 = new Letter("생일 축하합니다~!!", Tag.BIRTHDAY);
        Letter birthday3 = new Letter("생일 333", Tag.BIRTHDAY);

        Letter love1 = new Letter("100일 축", Tag.ANNIVERSARY);
        Letter love2 = new Letter("200일 축", Tag.ANNIVERSARY);

        List<Letter> letterList = Arrays.asList(birthday, birthday2, birthday3, love1, love2);
        this.letterRepository.saveAll(letterList);
        System.out.println(this.letterRepository.findAll());

    }
}
