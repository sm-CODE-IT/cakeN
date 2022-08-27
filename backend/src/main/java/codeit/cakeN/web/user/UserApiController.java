package codeit.cakeN.web.user;

import codeit.cakeN.config.auth.TokenProvider;
import codeit.cakeN.config.auth.dto.ResponseDto;
import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.letter.HeartRepository;
import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.domain.user.profileImg.File;
import codeit.cakeN.domain.user.profileImg.FileRepository;
import codeit.cakeN.domain.user.profileImg.ProfileStore;
import codeit.cakeN.exception.letter.LetterException;
import codeit.cakeN.exception.letter.LetterExceptionType;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.exception.user.UserExceptionType;
import codeit.cakeN.service.user.UserService;
import codeit.cakeN.web.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final ProfileStore profileStore;
    private final FileRepository fileRepository;
    private final HeartRepository heartRepository;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입
     * @param userRequestDto
     * @return
     */
    @PostMapping("/users")
    public Object createUser(UserRequestDto userRequestDto) {
        /*if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage = allErrors.get(0).getDefaultMessage();

            return new CreateError().error(errorMessage);
        }

        if (!(userRequestDto.getPw().equals(userRequestDto.getPwConfirm()))) {
            return new CreateError().error("패스워드가 일치하지 않습니다.");
        }
        if (userService.emailCheck(userRequestDto.getEmail())) {
            return new CreateError().error("이미 존재하는 회원입니다.");
        }*/


//        userRequestDto.setImage(userRequestDto.getImage());
        userService.save(userRequestDto);

        return userRequestDto.toEntity();
    }

    /**
     * 회원탈퇴 (비밀번호 확인)
     * @param userDeleteDto
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@Valid UserDeleteDto userDeleteDto, @PathVariable("id") Long id) throws Exception {
        try {
            userService.deleteUser(userDeleteDto.getPwConfirm(), id);
        } catch (UserException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    /**
     * 마이페이지(개인정보 조회)
     * @param formUser
     * @return
     */
    @GetMapping("/mypage")
    public ResponseEntity<UserRequestDto> myPage(@AuthenticationPrincipal org.springframework.security.core.userdetails.User formUser) {
        User user = findSessionUser(formUser, httpSession, userRepository);
        UserRequestDto userRequestDto = userService.getMyInfo(user.getUserId());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity(userRequestDto, header, HttpStatus.OK);
    }

    /**
     * 마이페이지(개인정보 조회) - id로 넘겨주기 방식
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserRequestDto> myPageId(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).get();
        UserRequestDto userRequestDto = userService.getMyInfo(user.getUserId());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity(userRequestDto, header, HttpStatus.OK);
    }
    
    /**
     * 개인정보 수정
     * @param id
     * @param userUpdateDto
     * @return
     */
    @PutMapping("/users/{id}")
    public ResponseEntity updateInfo(@PathVariable("id") Long id, UserUpdateDto userUpdateDto) {
        userService.update(id, userUpdateDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 개인정보 수정 - 비밀번호 변경
     * @param userUpdatePwDto
     * @param id
     * @return
     */
    @PutMapping("/users/password/{id}")
    public ResponseEntity updatePw(@Valid UserUpdatePwDto userUpdatePwDto, @PathVariable("id") Long id) {
        userService.updatePw(userUpdatePwDto.getPw(), userUpdatePwDto.getNewPwConfirm(), id);
        return ResponseEntity.ok().build();
    }

    /**
     * 좋아요 한 레터링 리스트 가져오기
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/heartletter")
    public List<Heart> getHeartLetter(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserException(UserExceptionType.NOT_FOUND_USER)
        );

        List<Heart> heartLetterList = heartRepository.findByUser(user).orElseThrow(
                () -> new LetterException(LetterExceptionType.NOT_FOUND_HEART)
        );
        System.out.println(heartLetterList);

        /*List<Letter> letterList = new ArrayList<>();
        for (Heart heart : heartLetterList) {
            letterList.add(heart.getLetter());
        }*/

        System.out.println(heartRepository.findByUser(user));
        System.out.println(heartLetterList);

        return heartLetterList;
    }

    @GetMapping("/users/heartletter")
    public List<Heart> getAllHeart() {
        return this.heartRepository.findAll();
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(UserLoginRequestDto loginRequestDto) {
        User user = userService.getByCredentials(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );

        if (user != null) {
            // 토큰 생성
            final String token = tokenProvider.create(user);

            UserLoginRequestDto responseUser = UserLoginRequestDto.builder()
                    .username(user.getEmail())
                    .password(user.getPw())
                    .role(Role.USER)
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUser);
        } else {
            ResponseDto responseDto = ResponseDto.builder()
                    .error("Login failed")
                    .build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @ResponseBody
    @GetMapping("/mypage/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + profileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{fileId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long fileId) throws MalformedURLException {
        File file = fileRepository.findById(fileId);
        String storeFileName = file.getAttachFile().getStoreFileName();
        String uploadFileName = file.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + profileStore.getFullPath(storeFileName));

        // 파일명을 인코딩하여 전달 for 한글 파일명
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }


}
