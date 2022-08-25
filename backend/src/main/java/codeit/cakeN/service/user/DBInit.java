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
        Letter birthday18 = new Letter("ㅅㅇㅊㅋㅎ", Tag.BIRTHDAY);
        Letter birthday19 = new Letter("생일축하한다람쥐", Tag.BIRTHDAY);

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

        Letter parentsday=new Letter("엄마아빠 키워주셔서 감사합니다", Tag.PARENTSDAY);
        Letter parentsday2=new Letter("엄마아빠 낳아주셔서 감사합니다", Tag.PARENTSDAY);
        Letter parentsday3=new Letter("오래오래 건강하세요 사랑합니다", Tag.PARENTSDAY);
        Letter parentsday4=new Letter("다시 태어나도 엄마아빠 아들 -창민-", Tag.PARENTSDAY);
        Letter parentsday5=new Letter("항상 감사하고 사랑합니다", Tag.PARENTSDAY);
        Letter parentsday6=new Letter("오래오래 함께 해주세요", Tag.PARENTSDAY);
        Letter parentsday7=new Letter("엄마아빠 딸이어서 너무 행복해", Tag.PARENTSDAY);
        Letter parentsday8=new Letter("Happy Parents Day", Tag.PARENTSDAY);
        Letter parentsday9=new Letter("사랑으로 키워주셔서 감사합니다", Tag.PARENTSDAY);
        Letter parentsday10=new Letter("엄마아빠 사랑해용", Tag.PARENTSDAY);
        Letter parentsday11=new Letter("엄마아빠 늘 고마워요", Tag.PARENTSDAY);

        Letter teachersday=new Letter("스승의 은혜는 하늘같아서~", Tag.TEACHERSDAY);
        Letter teachersday2=new Letter("선생님 사랑합니다♡", Tag.TEACHERSDAY);
        Letter teachersday3=new Letter("카네이션 대신 이걸 준비했어요~", Tag.TEACHERSDAY);
        Letter teachersday4=new Letter("Dear.My Teacher", Tag.TEACHERSDAY);
        Letter teachersday5=new Letter("Happy Teachers Day", Tag.TEACHERSDAY);
        Letter teachersday6=new Letter("존경합니다 사랑합니다", Tag.TEACHERSDAY);
        Letter teachersday7=new Letter("I admire U", Tag.TEACHERSDAY);
        Letter teachersday8=new Letter("우리의 영원한 선생님", Tag.TEACHERSDAY);
        Letter teachersday9=new Letter("사부님으로 모시겠습니다", Tag.TEACHERSDAY);
        Letter teachersday10=new Letter("내 영원한 스승^^", Tag.TEACHERSDAY);
        Letter teachersday11=new Letter("선생님의 은혜에 깊이 감사드립니다.", Tag.TEACHERSDAY);
        Letter teachersday12=new Letter("뜻깊은 스승의날 축하드립니다.", Tag.TEACHERSDAY);

        Letter christmas = new Letter("창민아 울어도 돼, 사실 산타는 없거든:)", Tag.CHRISTMAS);
        Letter christmas2 = new Letter("창민아 울면 안 돼, 사실 산타는 있거든:)", Tag.CHRISTMAS);
        Letter christmas3 = new Letter("merry christmas", Tag.CHRISTMAS);
        Letter christmas4 = new Letter("MERRY X-MAS", Tag.CHRISTMAS);
        Letter christmas5 = new Letter("메리 크리스마스!", Tag.CHRISTMAS);
        Letter christmas6 = new Letter("해피 크리스마스", Tag.CHRISTMAS);
        Letter christmas7 = new Letter("따듯한 성탄절", Tag.CHRISTMAS);
        Letter christmas8 = new Letter("예수님 등장", Tag.CHRISTMAS);
        Letter christmas9 = new Letter("핵꿀잼ㅋㅋㅋ리스마스", Tag.CHRISTMAS);
        Letter christmas10 = new Letter("썰매를 끌어줄 돌프 어디 없나", Tag.CHRISTMAS);

        Letter anniversary=new Letter("우리가 만난지 200일",Tag.ANNIVERSARY);
        Letter anniversary2=new Letter("우리가 만난지 13030분",Tag.ANNIVERSARY);
        Letter anniversary3=new Letter("우리가 만난지 182971212시간",Tag.ANNIVERSARY);
        Letter anniversary4=new Letter("우리가 만난지 2167389028765789281778초",Tag.ANNIVERSARY);
        Letter anniversary5=new Letter("우리 사랑 영원하자",Tag.ANNIVERSARY);
        Letter anniversary6=new Letter("나밖에 없지?",Tag.ANNIVERSARY);
        Letter anniversary7=new Letter("다음 생에도 창민이의 발닦개가 될게",Tag.ANNIVERSARY);
        Letter anniversary8=new Letter("변화는 있되 변함은 없는 우리",Tag.ANNIVERSARY);
        Letter anniversary9=new Letter("익숙함에 속아 서로를 잊지 말자",Tag.ANNIVERSARY);
        Letter anniversary10=new Letter("창민♡민창's 100 DAY",Tag.ANNIVERSARY);
        Letter anniversary11=new Letter("숨참고 Love Dive",Tag.ANNIVERSARY);
        Letter anniversary12=new Letter("내가 널 많이 사랑한 죄~",Tag.ANNIVERSARY);

        Letter leave=new Letter("창민 is Free",Tag.LEAVE);
        Letter leave2=new Letter("안녕히계세요 여러분~",Tag.LEAVE);
        Letter leave3=new Letter("오늘부터 자유다",Tag.LEAVE);
        Letter leave4=new Letter("백수 된 걸 축하해",Tag.LEAVE);
        Letter leave5=new Letter("백수 again..",Tag.LEAVE);
        Letter leave6=new Letter("그동안 수고 많았어",Tag.LEAVE);
        Letter leave7=new Letter("부장님 그동안 수고 많으셨습니다",Tag.LEAVE);
        Letter leave8=new Letter("평생 네 케이크는 내가 사줄게",Tag.LEAVE);
        Letter leave9=new Letter("ㅇㅇ회사 우짤래미 저짤래미",Tag.LEAVE);
        Letter leave10=new Letter("만수무강 도비하셈~",Tag.LEAVE);

        Letter employment=new Letter("오늘부터 월급의 노예",Tag.EMPLOYMENT);
        Letter employment2=new Letter("위풍당당 창민이 취뽀 축하~",Tag.EMPLOYMENT);
        Letter employment3=new Letter("이제부터 돈길만 걷자~",Tag.EMPLOYMENT);
        Letter employment4=new Letter("ㅇㅇ회사는 계탔네 창민이 데려가서",Tag.EMPLOYMENT);
        Letter employment5=new Letter("ㅇㅇ회사 다크호스 출두~",Tag.EMPLOYMENT);
        Letter employment6=new Letter("돈많이 벌어서 고기사줘",Tag.EMPLOYMENT);
        Letter employment7=new Letter("창민아 꽃길만 걷자",Tag.EMPLOYMENT);
        Letter employment8=new Letter("취업 힘들면 말해 나랑 유튜브 하자",Tag.EMPLOYMENT);
        Letter employment9=new Letter("고생 끝 행복 시작 축 취직",Tag.EMPLOYMENT);
        Letter employment10=new Letter("취업을 축하합니다~",Tag.EMPLOYMENT);
        Letter employment11=new Letter("아들~ 해낼 줄 알았어~",Tag.EMPLOYMENT);
        Letter employment12=new Letter("딸~ 취업 축하해!",Tag.EMPLOYMENT);

        Letter friendship=new Letter("내 인생의 로또는 창민♡",Tag.FRIENDSHIP);
        Letter friendship2=new Letter("이번 달 친구 비용 5만원 입금 부탁",Tag.FRIENDSHIP);
        Letter friendship3=new Letter("우리 우정 실버타운까지..",Tag.FRIENDSHIP);
        Letter friendship4=new Letter("창민이는 좋겠다 우리가 친구라서",Tag.FRIENDSHIP);
        Letter friendship5=new Letter("오늘 너네 집에서 파티할 건데 너도 올래?",Tag.FRIENDSHIP);
        Letter friendship6=new Letter("창민이가 마신 소주만큼 우정해",Tag.FRIENDSHIP);
        Letter friendship7=new Letter("평생 니 케이크는 내가 사줄게",Tag.FRIENDSHIP);
        Letter friendship8=new Letter("나밖에 없지?",Tag.FRIENDSHIP);
        Letter friendship9=new Letter("항상 창민이를 응원하는 민창이가",Tag.FRIENDSHIP);
        Letter friendship10=new Letter("창민아 하고 싶은 거 다 해",Tag.FRIENDSHIP);
        Letter friendship11=new Letter("창민아 오늘도 좋은 하루 보내 넌 나의 첫번째니까",Tag.FRIENDSHIP);

        Letter love1 = new Letter("100일 축", Tag.ANNIVERSARY);
        Letter love2 = new Letter("200일 축", Tag.ANNIVERSARY);

        List<Letter> letterList = Arrays.asList(birthday2, birthday3,birthday4,birthday5,birthday6,birthday7,birthday8,birthday9,birthday10,birthday11,birthday12,birthday13,birthday14,birthday15,birthday16,birthday17,birthday18,birthday19, birthday, Birthday, Birthday2, Birthday3,Birthday4,Birthday5,Birthday6,Birthday7,Birthday8,Birthday9,Birthday10,Birthday11,Birthday12,Birthday13,Birthday14,Birthday15,Birthday16, parentsday, parentsday2, parentsday3, parentsday4, parentsday5, parentsday6, parentsday7, parentsday8, parentsday9, parentsday10, parentsday11, teachersday,teachersday2, teachersday3, teachersday4, teachersday5, teachersday6, teachersday7, teachersday8, teachersday9, teachersday10, teachersday11, teachersday12, teachersday12, christmas, christmas2, christmas3, christmas4, christmas5, christmas6, christmas7, christmas8, christmas9, christmas10, anniversary, anniversary2, anniversary3, anniversary4, anniversary5,anniversary6, anniversary7, anniversary8, anniversary9, anniversary10, anniversary11, anniversary12, leave,leave2, leave3, leave4, leave5, leave6, leave7, leave8, leave9, leave10, employment,employment2, employment3, employment4, employment5, employment6, employment7, employment8, employment9, employment10, employment11, employment12, friendship, friendship2, friendship3, friendship4, friendship5, friendship6, friendship7, friendship8, friendship9, friendship10, friendship11, love1, love2);
        this.letterRepository.saveAll(letterList);
        System.out.println(this.letterRepository.findAll());

    }
}
