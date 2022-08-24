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


        Letter birthday = new Letter("HBD", Tag.BIRTHDAY);
        Letter birthday2 = new Letter("Happy Birthday Changmin", Tag.BIRTHDAY);
        Letter birthday3 = new Letter("Happy 36th Birthday!", Tag.BIRTHDAY);
        Letter birthday4 = new Letter("창민아! 생일 축하한다!", Tag.BIRTHDAY);
        Letter birthday5 = new Letter("System.out.println(\"생축\");", Tag.BIRTHDAY);
        Letter birthday6 = new Letter("printf(\"생축\");", Tag.BIRTHDAY);
        Letter birthday7 = new Letter("눈부신 계절에 태어나줘서 고마워", Tag.BIRTHDAY);
        Letter birthday8 = new Letter("Birthday girl☆", Tag.BIRTHDAY);
        Letter birthday9 = new Letter("Happy Changmin Day", Tag.BIRTHDAY);
        Letter birthday10 = new Letter("Happy 36th Birthday!", Tag.BIRTHDAY);
        Letter birthday11 = new Letter("꽃가루를 날려~ 폭죽을 더 크게 터뜨려~", Tag.BIRTHDAY);
        Letter birthday12 = new Letter("창민 씨, 생일 축하를 받아야겠군.", Tag.BIRTHDAY);
        Letter birthday13 = new Letter("축하해. 생일을.", Tag.BIRTHDAY);
        Letter birthday14 = new Letter("미치겠다. 생일 축하 받고 싶어요?", Tag.BIRTHDAY);
        Letter birthday15 = new Letter("엄마의 딸/아들이 되어줘서 고마워", Tag.BIRTHDAY);
        Letter birthday16 = new Letter("사랑하는 내 친구 창민아 생일 축하해", Tag.BIRTHDAY);
        Letter birthday17 = new Letter("반오십 축하해", Tag.BIRTHDAY);
        Letter birthday18 = new Letter("ㅊㅋ", Tag.BIRTHDAY);

        Letter Birthday = new Letter("사랑하는 엄마 생신 축하합니다", Tag.BIRTHDAY2);
        Letter Birthday2 = new Letter("생신 축하드려요~", Tag.BIRTHDAY2);
        Letter Birthday3 = new Letter("인생은 60부터", Tag.BIRTHDAY2);
        Letter Birthday4 = new Letter("우리가 제일 좋아하는 빵은 아빵", Tag.BIRTHDAY2);
        Letter Birthday5 = new Letter("엄마 사랑해요", Tag.BIRTHDAY2);
        Letter Birthday6 = new Letter("꽃길만 걷게 해줄게요", Tag.BIRTHDAY2);
        Letter Birthday7 = new Letter("생일 축하해~ 역시 딸밖에 없지?", Tag.BIRTHDAY2);
        Letter Birthday8 = new Letter("우주에서 가장 소중한 우리엄마 생일 축하해", Tag.BIRTHDAY2);
        Letter Birthday9 = new Letter("세상에서 가장 멋진 우리 여사님 존경합니다", Tag.BIRTHDAY2);
        Letter Birthday10 = new Letter("아바마마의 생신을 감축드리옵니다", Tag.BIRTHDAY2);
        Letter Birthday11 = new Letter("환갑 축하드려요 늘 건강하세요", Tag.BIRTHDAY2);
        Letter Birthday12 = new Letter("울엄마 추카추카", Tag.BIRTHDAY2);
        Letter Birthday13 = new Letter("47번째 생신 축하드려요~", Tag.BIRTHDAY2);
        Letter Birthday14 = new Letter("미치겠다. 생일 축하 받고 싶어요?", Tag.BIRTHDAY2);
        Letter Birthday15 = new Letter("엄마의 딸/아들이 되어줘서 고마워", Tag.BIRTHDAY2);
        Letter Birthday16 = new Letter("사랑하는 내 친구 창민아 생일 축하해", Tag.BIRTHDAY2);

        Letter love1 = new Letter("100일 축", Tag.ANNIVERSARY);
        Letter love2 = new Letter("200일 축", Tag.ANNIVERSARY);

        List<Letter> letterList = Arrays.asList(birthday, birthday2, birthday3,birthday4,birthday5,birthday6,birthday7,birthday8,birthday9,birthday10,birthday11,birthday12,birthday13,birthday14,birthday15,birthday16,birthday17,birthday18, Birthday, Birthday2, Birthday3,Birthday4,Birthday5,Birthday6,Birthday7,Birthday8,Birthday9,Birthday10,Birthday11,Birthday12,Birthday13,Birthday14,Birthday15,Birthday16, love1, love2);
        this.letterRepository.saveAll(letterList);
        System.out.println(this.letterRepository.findAll());

    }
}
