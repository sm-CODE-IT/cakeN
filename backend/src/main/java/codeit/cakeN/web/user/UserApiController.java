package codeit.cakeN.web.user;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.domain.user.profileImg.File;
import codeit.cakeN.domain.user.profileImg.FileRepository;
import codeit.cakeN.domain.user.profileImg.ProfileStore;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.service.user.UserService;
import codeit.cakeN.web.user.dto.UserDeleteDto;
import codeit.cakeN.web.user.dto.UserRequestDto;
import codeit.cakeN.web.user.dto.UserUpdateDto;
import codeit.cakeN.web.user.dto.UserUpdatePwDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final ProfileStore profileStore;
    private final FileRepository fileRepository;

    /**
     * 회원가입
     * @param userRequestDto
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid UserRequestDto userRequestDto) {
        userRequestDto.setImage(userRequestDto.getImage());
        userService.save(userRequestDto);

        return ResponseEntity.ok(userRequestDto.toEntity());
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
    public ResponseEntity updateInfo(@PathVariable("id") Long id, UserUpdateDto userUpdateDto, @RequestParam("image") MultipartFile multipartFile) {
        userService.update(userUpdateDto, multipartFile);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/users/password/{id}")
    public ResponseEntity updatePw(@Valid UserUpdatePwDto userUpdatePwDto, @PathVariable("id") Long id) {
        userService.updatePw(userUpdatePwDto.getPw(), userUpdatePwDto.getNewPwConfirm(), id);
        return ResponseEntity.ok().build();
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
