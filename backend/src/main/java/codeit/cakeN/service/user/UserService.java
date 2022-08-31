package codeit.cakeN.service.user;

import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.exception.user.UserExceptionType;
import codeit.cakeN.web.user.dto.UserRequestDto;
import codeit.cakeN.web.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


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


    @Value("${codeit.cakeN.upload.path}")
    private String filePath;

    /**
     * 회원정보 수정
     * @param userUpdateDto
     * @throws Exception
     */
    @Transactional
    public void update(Long id, UserUpdateDto userUpdateDto) throws UserException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserException(UserExceptionType.NOT_FOUND_USER)
        );

        /*String imageFileName = user.getUserId() + "_" + multipartFile.getOriginalFilename();
        Path imageFilePath = Paths.get(filePath + imageFileName);

        // 파일이 없로드 되었는지 확인
        if (multipartFile.getSize() != 0) {
            try {
                if (user.getImage() != null) {
                    File file = new File(filePath + user.getImage());
                    file.delete();  // 이미 프로필 사진이 존재하는 경우, 기존 파일은 삭제
                }
                Files.write(imageFilePath, multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setImage(imageFileName);
        }
*/
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

    /**
     * 로그인
     * @param loginId
     * @param password
     * @return
     */
    public User getByCredentials(final String loginId, final String password) {

        Optional<User> findUser = userRepository.findByEmail(loginId);
        User user = findUser.get();   // Optional로 감싼 형태에서 꺼내기
        if (passwordEncoder.matches(password, user.getPw())) {    // matches 메서드를 이용해 패스워드의 일치 여부 확인
            return user;
        } else {
            return null;
        }
    }
}
