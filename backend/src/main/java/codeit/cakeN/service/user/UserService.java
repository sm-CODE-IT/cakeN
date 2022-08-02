package codeit.cakeN.service.user;

import codeit.cakeN.config.auth.SecurityUtil;
import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.web.dto.UserRequestDto;
import codeit.cakeN.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // 회원 가입
    @Transactional
    public void save(UserRequestDto requestDto) {
        requestDto.setRole(Role.USER);
        String enPw = passwordEncoder.encode(requestDto.getPw());
        requestDto.setPw(enPw);
        userRepository.save(requestDto.toEntity());
    }

    // 회원 탈퇴
    public void deleteUser(String checkPw) throws Exception {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUser()).orElseThrow(
                () -> new UsernameNotFoundException("존재하지 않는 사용자입니다.")
        );
        if (!user.matchPw(passwordEncoder, checkPw)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);
    }

    // 회원 정보 수정
    @Transactional
    public void update(UserUpdateDto userUpdateDto) throws Exception {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUser()).orElseThrow(
                () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
        );

        userUpdateDto.nickname().ifPresent(user::updateNickname);
        userUpdateDto.intro().ifPresent(user::updateIntro);
        userUpdateDto.image().ifPresent(user::updateImage);
    }

    // 이메일 중복 체크(소셜 로그인-기본 로그인 충돌)
    public boolean emailCheck(String email) {
        return userRepository.existsByEmail(email);
    }

    // 회원 정보 수정 - 이메일은 따로 분리
    public void updatePw(String checkPw, String newPw) throws Exception {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUser()).orElseThrow(
                () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
        );

        if (!user.matchPw(passwordEncoder, checkPw)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        user.updatePw(passwordEncoder, newPw);
    }

    // 마이페이지 개인 정보 조회
    UserRequestDto getMyInfo() throws Exception {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUser()).orElseThrow(
                () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
        );
        return new UserRequestDto(user);
    }

    /*@Transactional
    public void encryptPassword(String userPw) {
        User user = new User();
        String enPw = passwordEncoder.encode(userPw);
        user.setPw(enPw);
    }*/

    /*@Transactional
    public Long update(Long id, UserRequestDto requestDto) {
        User user1 = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

        user1.update(requestDto);

        return user1.getUserId();
    }*/


/*    private List<SimpleGrantedAuthority> getRoles(List<Role> roles) {
        return roles.stream()
                .map(Role::getKey)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }*/
}
