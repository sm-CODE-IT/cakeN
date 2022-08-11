package codeit.cakeN.service.user;

import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.web.user.dto.UserRequestDto;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    String PASSWORD = "password";

    public void clear() {
        em.flush();
        em.clear();
    }

    /*public UserRequestDto createUserDto() {
        return new UserRequestDto(new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun",
                Role.USER
        ));
    }

    public UserRequestDto setUser() throws Exception {
        UserRequestDto userRequestDto = createUserDto();
        userService.save(userRequestDto);
        clear();
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();

        emptyContext.setAuthentication(new UsernamePasswordAuthenticationToken(User.builder()
                .email(userRequestDto.getEmail())
                .pw(userRequestDto.getPw())
                .intro(userRequestDto.getIntro())
                .image(userRequestDto.getImage())
                .nickname(userRequestDto.getNickname())
                .role(userRequestDto.getRole())
                .build(),
                null, null
        ));
        SecurityContextHolder.setContext(emptyContext);
        return userRequestDto;

    }*/

    @AfterEach
    public void deleteUser() {
        SecurityContextHolder.createEmptyContext().setAuthentication(null);
    }

    /**
     * 회원가입 테스트
     */
    @Test
    public void 회원가입_성공() throws Exception {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun",
                Role.USER
        ));

        //when
        userService.save(userRequestDto);

        //then
        User user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(
                () -> new Exception("존재하지 않는 회원입니다.")
        );
        assertThat(user.getUserId()).isNotNull();
        assertThat(user.getEmail()).isEqualTo(userRequestDto.getEmail());
        assertThat(user.getRole()).isEqualTo(userRequestDto.getRole());

    }

    /**
     * 회원정보 수정 테스트
     * 회원가입을 하지 않은 사람이 수정을 시도할 때 오류 (Security Filter 적용)
     * 아이디는 변경 불가
     * 비밀번호 변경 시, 현재 비밀번호를 입력받아서 일치한지 검증 후 통과
     */
    @Test
    public void 회원정보_비밀번호_수정() throws Exception {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun",
                Role.USER
        ));
        
        
        //when
        String newPw = "1234567890!";
        userService.updatePw(PASSWORD, newPw, userRequestDto.getId());
        clear();

        //then
        userRepository.findByEmail(userRequestDto.getEmail()).ifPresent(
                user -> {
                    assertThat(user.matchPw(passwordEncoder, newPw)).isTrue();
                }
        );
    }

    @Test
    public void 회원정보_일부_수정() throws Exception {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun",
                Role.USER
        ));

        //when
        String updateNickname = "변경내용";
        String updateIntro = "안녕안녕";
//        userService.update(new UserUpdateDto(Optional.of(updateNickname), Optional.of(updateIntro), Optional.empty()));
        clear();

        //then
        userRepository.findByEmail(userRequestDto.getEmail()).ifPresent((
                user -> {
                    assertThat(user.getNickname()).isEqualTo(updateNickname);
                    assertThat(user.getIntro()).isEqualTo(updateIntro);
                }));

    }

    /**
     * 회원 탈퇴
     * 비밀번호 인증 후 탈퇴 처리
     */
    @Test
    public void 회원탈퇴() throws Exception {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun",
                Role.USER
        ));

        //when
        userService.deleteUser(PASSWORD, userRequestDto.getId());

        //then
        assertThat(assertThrows(Exception.class, () -> userRepository.findByEmail(userRequestDto.getEmail())
                .orElseThrow(() -> new Exception())));

    }

    @Test
    public void 회원탈퇴_실패() throws Exception {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun",
                Role.USER
        ));

        //when, then
        assertThat(assertThrows(Exception.class, () -> userService.deleteUser(PASSWORD + "!", userRequestDto.getId()))
                .getMessage()).isEqualTo("비밀번호가 일치하지 않습니다.");

    }

    /**
     * 회원정보 조회
     */
    @Test
    public void 내정보조회() throws Exception {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(new User(
                "jun020216@naver.com",
                "****",
                "안녕",
                "11.jpg",
                "jun",
                Role.USER
        ));

        //when
        UserRequestDto myInfo = userService.getMyInfo(userRequestDto.toEntity().getUserId());

        //then
        assertThat(myInfo.getEmail()).isEqualTo(userRequestDto.getEmail());
        assertThat(myInfo.getNickname()).isEqualTo(userRequestDto.getNickname());

    }
}