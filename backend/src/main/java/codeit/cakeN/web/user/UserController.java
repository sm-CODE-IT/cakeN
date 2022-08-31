package codeit.cakeN.web.user;

//import codeit.cakeN.config.auth.TokenProvider;
import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.letter.HeartRepository;
import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.profileImg.File;
import codeit.cakeN.domain.user.profileImg.FileRepository;
import codeit.cakeN.domain.user.profileImg.ProfileStore;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.exception.user.UserExceptionType;
import codeit.cakeN.service.user.UserService;
import codeit.cakeN.web.letter.dto.HeartDto;
import codeit.cakeN.web.user.dto.*;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;


//@RestController
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final ProfileStore profileStore;
    private final FileRepository fileRepository;
    private final HeartRepository heartRepository;
//    private final TokenProvider tokenProvider;

    /**
     * 회원가입 페이지
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "user/createUserForm";
    }

    /**
     * 회원가입 로직
     * @param userRequestDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/new")
    public String createUser(@Valid UserRequestDto userRequestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/createUserForm";
        }

        userRequestDto.setImage(userRequestDto.getImage());

        try {
            if (!(userRequestDto.getPw().equals(userRequestDto.getPwConfirm()))) {
                bindingResult.rejectValue("pwConfirm", "pwConfirm", "패스워드가 일치하지 않습니다.");
                return "user/createUserForm";
            }
            if (userService.emailCheck(userRequestDto.getEmail())) {
                bindingResult.rejectValue("email", "emailDuplicate", "이미 존재하는 회원입니다.");
                return "user/createUserForm";
            }
            userService.save(userRequestDto);

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/createUserForm";
        }

        return "redirect:/users/login";    // 회원가입 성공 시 로그인 페이지로 리다이렉트
    }


    /**
     * 회원탈퇴 페이지
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userDeleteDto", new UserDeleteDto());
        model.addAttribute("userNickname", userRepository.findById(id).get().getNickname());
        model.addAttribute("userEmail", userRepository.findById(id).get().getEmail());
        return "user/deleteForm";
    }

    /**
     * 회원탈퇴 로직
     * @param userDeleteDto
     * @param bindingResult
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@Valid UserDeleteDto userDeleteDto, BindingResult bindingResult, @PathVariable("id") Long id, Model model) throws Exception {

        if (bindingResult.hasErrors())
            return "user/deleteForm";

        try {
            userService.deleteUser(userDeleteDto.getPwConfirm(), id);
        } catch (UserException e) {
            model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            return "user/deleteForm";

        } catch (Exception e) {
            e.printStackTrace();
            return "user/deleteForm";

        }

        return "redirect:/users/login";
    }


    /**
     * 로그인 페이지
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false)String error, @RequestParam(value = "exception", required = false)String exception, Model model) {
        model.addAttribute("userLoginRequestDto", new UserLoginRequestDto());

        // 로그인 실패 시 에러 메시지 출력
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserLoginRequestDto loginRequestDto) {
        codeit.cakeN.domain.user.User user = userService.getByCredentials(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );

        if (user != null) {
            // 토큰 발급
//            final String token = tokenProvider.create(user);

            UserLoginRequestDto responseUser = UserLoginRequestDto.builder()
                    .username(user.getEmail())
                    .password(user.getPw())
                    .role(Role.USER)
//                    .token(token)
                    .build();
            return "redirect:/";
        } else {
            return "redirect:/users/login";
        }
    }


    /**
     * 마이페이지 (개인정보 조회)
     */
    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal User formUser) {

        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);

        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userNickname", user.getNickname());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userIntro", user.getIntro());
        List<Heart> heart = heartRepository.findByUser(user).get();
        model.addAttribute("hearts", heart);

        // 내가 자랑한 콘테스트 게시물 모아보기
        model.addAttribute("contests", user.getContestList());
        return "user/mypage";
    }

    public static codeit.cakeN.domain.user.User findSessionUser(@AuthenticationPrincipal User formUser, HttpSession httpSession, UserRepository userRepository) throws UserException {
        SecurityUser oauthUser = (SecurityUser) httpSession.getAttribute("user");
        codeit.cakeN.domain.user.User user = null;

        try {
            if (oauthUser != null) {
                user = userRepository.findByEmail(oauthUser.getEmail()).get();
            }

            if (formUser != null) {
                user = userRepository.findByEmail(formUser.getUsername()).get();
            }
        } catch (UserException e) {
            System.out.println(UserExceptionType.NOT_FOUND_USER.getErrorMessage());
        }

        return user;
    }

    @GetMapping("/mypage/heart-letter")
    public String heartLetter(@AuthenticationPrincipal User formUser, Model model) {
        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);

        model.addAttribute("userNickname", user.getNickname());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userId", user.getUserId());

        List<Heart> heart = heartRepository.findByUser(user).get();
        model.addAttribute("hearts", heart);


        return "user/letterHeart";
    }


    /**
     * 개인정보 수정 페이지
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateInfo(Model model, @PathVariable("id") Long id) {
        UserRequestDto userRequestDto = userService.getMyInfo(id);
        model.addAttribute("userRequestDto", userRequestDto);
        return "user/updateUserForm.html";
    }

    /**
     * 개인정보 수정 로직
     * @param userRequestDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/update/{id}")
    public String updateInfo(@PathVariable("id") Long id, UserUpdateDto userRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/updateUserForm.html";
        }

        userService.update(id, userRequestDto);

        return "redirect:/users/mypage";
    }


    //TODO 개인정보 수정 페이지 내에 iframe 형태로 삽입 or 다른 방식으로 같은 페이지 내에서 처리되도록
    /**
     * 개인정보 수정 - 비밀번호 변경 페이지
     * @param model
     * @return
     */
    @GetMapping("/update-pw/{id}")
    public String updatePw(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User formUser) {
        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);
        model.addAttribute("userNickname", user.getNickname());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userUpdatePwDto", new UserUpdatePwDto());

        return "user/updatePwForm";
    }


    /**
     * 개인정보 수정 - 비밀번호 변경 로직
     * @param userUpdatePwDto
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/update-pw/{id}")
    public String updatePw(@Valid UserUpdatePwDto userUpdatePwDto, @PathVariable("id") Long id, BindingResult bindingResult, Model model) throws Exception {

        if (bindingResult.hasErrors())
            return "user/updatePwForm";

        try {
            userService.updatePw(userUpdatePwDto.getPw(), userUpdatePwDto.getNewPwConfirm(), id);
        } catch (UserException e) {
            model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            return "user/updatePwForm";
        } catch (Exception e) {
            e.printStackTrace();
            return "user/updatePwForm";
        }

        return "redirect:/users/mypage";
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

        log.info("uploadFileName={}", uploadFileName);

        // 파일명을 인코딩하여 전달 for 한글 파일명
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
