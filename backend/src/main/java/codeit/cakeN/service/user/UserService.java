package codeit.cakeN.service.user;

import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.exception.user.UserExceptionType;
import codeit.cakeN.web.dto.UserRequestDto;
import codeit.cakeN.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * 회원가입
     * @param requestDto
     */
    @Transactional
    public void save(UserRequestDto requestDto) {
        
        // USER 권한 부여 
        requestDto.setRole(Role.USER);
        
        // 회원 가입 시 입력받은 비밀번호 암호화
        String enPw = passwordEncoder.encode(requestDto.getPw());
        requestDto.setPw(enPw);
        
        // 이미 존재하는 아이디로 가입 시도 시 예외 발생
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new UserException(UserExceptionType.ALREADY_EXIST_USERNAME);
        }
        
        // 회원 가입 완료
        userRepository.save(requestDto.toEntity());
    }

    /**
     * 회원 탈퇴
     * @param checkPw
     * @param id
     * @throws UserException
     */
    @Transactional
    public void deleteUser(String checkPw, Long id) throws UserException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserException(UserExceptionType.NOT_FOUND_USER)
        );
        
        // 비밀번호가 일치하지 않으면 탈퇴 실패
        if (!user.matchPw(passwordEncoder, checkPw)) {
            throw new UserException(UserExceptionType.WRONG_PASSWORD);
        }

        userRepository.delete(user);
    }


    /**
     * 회원정보 수정
     * @param userUpdateDto
     * @throws Exception
     */
    @Transactional
    public void update(UserUpdateDto userUpdateDto) throws UserException {
        User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(
                () -> new UserException(UserExceptionType.NOT_FOUND_USER)
        );

        user.update(userUpdateDto);
    }



    /**
     * 이메일 중복 체크 (소셜 로그인-기본 로그인 충돌)
     * @param email
     * @return
     */
    public boolean emailCheck(String email) {
        return userRepository.existsByEmail(email);
    }


    /**
     * 회원 정보 수정 -> 비밀번호 변경 시 일치 여부 체크
     * @param checkPw
     * @param newPw
     * @throws Exception
     */
    @Transactional
    public void updatePw(String checkPw, String newPw, Long id) throws UserException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserException(UserExceptionType.NOT_FOUND_USER)
        );

        if (!user.matchPw(passwordEncoder, checkPw)) {
            throw new UserException(UserExceptionType.WRONG_PASSWORD);
        }
        user.updatePw(passwordEncoder, newPw);   // 새로운 비밀번호를 암호화하여 저장
    }


    /**
     * 개인정보 조회 => 마이페이지, 상단바에서 사용
     * @param userId
     * @return
     * @throws Exception
     */
    public UserRequestDto getMyInfo(Long userId) throws UserException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException(UserExceptionType.NOT_FOUND_USER)
        );
        return new UserRequestDto(user);
    }

}
