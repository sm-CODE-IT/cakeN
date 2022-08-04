package codeit.cakeN.web;


import codeit.cakeN.config.auth.SecurityUtil;

import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.web.dto.UserDeleteDto;
import codeit.cakeN.web.dto.UserLoginRequestDto;
import codeit.cakeN.web.dto.UserRequestDto;
import codeit.cakeN.service.user.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


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
    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("userDeleteDto", new UserDeleteDto());
        return "user/deleteForm";
    }

    /**
     * 회원탈퇴 로직
     * @param userDeleteDto
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String deleteUser(@Valid UserDeleteDto userDeleteDto, @AuthenticationPrincipal User formUser, Model model) throws Exception {

        SecurityUser oauthUser = (SecurityUser) httpSession.getAttribute("user");
        String findUser = null;

        if (oauthUser != null) {
            findUser = oauthUser.getEmail();
        } else if (formUser != null) {
            findUser = formUser.getUsername();
        }

        try {
            userService.deleteUser(userDeleteDto.getPwConfirm(), findUser);
//            userService.deleteUser(userDeleteDto.getPw(), userDeleteDto.getEmail());
        } catch (UserException e) {
            model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            return "user/deleteForm";

        } catch (Exception e) {
            e.printStackTrace();
            return "user/deleteForm";

        }

        return "redirect:/";
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

        SecurityUser oauthUser = (SecurityUser) httpSession.getAttribute("user");
        codeit.cakeN.domain.user.User user = null;

        if (oauthUser != null) {
            user = userRepository.findByEmail(oauthUser.getEmail()).get();
        }

        if (formUser != null) {
            user = userRepository.findByEmail(formUser.getUsername()).get();
        }

        model.addAttribute("userNickname", user.getNickname());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userIntro", user.getIntro());

        return "user/mypage";
    }


    @GetMapping("/update")
    public String updateInfo(Model model) {
        return "user/update";
    }

    /*@PostMapping("/login")
    public String loginForm(@Valid UserLoginRequestDto loginUser, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/login";
        }
        bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");



        return "redirect:/";
    }*/


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
