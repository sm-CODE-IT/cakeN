package codeit.cakeN.web.user;


import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.exception.user.UserExceptionType;
import codeit.cakeN.service.user.UserService;
import codeit.cakeN.web.user.dto.*;
import lombok.RequiredArgsConstructor;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

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

        return "redirect:/";
    }


    /**
     * 회원탈퇴 페이지
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(Model model) {
        model.addAttribute("userDeleteDto", new UserDeleteDto());
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
    public String updateInfo(UserUpdateDto userRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/updateUserForm.html";
        }

        userService.update(userRequestDto);

        return "redirect:/users/mypage";
    }


    //TODO 개인정보 수정 페이지 내에 iframe 형태로 삽입 or 다른 방식으로 같은 페이지 내에서 처리되도록
    /**
     * 개인정보 수정 - 비밀번호 변경 페이지
     * @param model
     * @return
     */
    @GetMapping("/update-pw/{id}")
    public String updatePw(Model model) {
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


    /*
     * rest API
     */

    /*// Create
    //*public User createUser(@RequestBody UserRequestDto requestDto) {
        User user = new User(requestDto);
        return userRepository.save(user);
    }*//*

    // Read (전체)
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Read (개별)
    @GetMapping("/api/users/{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return Optional.of(userRepository.findById(userId));
    }

    // Update -> Patch / Put
    @PutMapping("/api/users/{userId}")
    public Long updateUser(@PathVariable Long userId, @RequestBody UserRequestDto requestDto) {
        return userService.update(userId, requestDto);
    }

    // Delete
    @DeleteMapping("/api/users/{userId}")
    public Long deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return userId;
    }*/
}
