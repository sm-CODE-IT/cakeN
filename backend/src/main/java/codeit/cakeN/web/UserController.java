package codeit.cakeN.web;

import codeit.cakeN.config.auth.LoginUser;
import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.CustomUserDetails;
import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.service.user.LoginService;
import codeit.cakeN.web.dto.UserLoginRequestDto;
import codeit.cakeN.web.dto.UserRequestDto;
import codeit.cakeN.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    ///////// 회원가입 ///////////
    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "user/createUserForm";
    }

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

    /////// 회원 탈퇴 ////////
    @GetMapping("/delete")
    public String delete() {
        return "user/deleteForm";
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody CustomUserDetails user, HttpSession session) throws Exception {
        if (user.getPassword().equals(session)) {
            session.removeAttribute("login");
            session.invalidate();
        }
    }


    /////// 로그인 ////////
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userLoginRequestDto", new UserLoginRequestDto());

        return "user/login";
    }

    @PostMapping("/login")
    public String loginForm(@Valid UserLoginRequestDto loginUser, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/login";
        }
        User isLogin = loginService.login(loginUser.getLoginId(), loginUser.getPassword());

        if (isLogin == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/login";
        }


        return "redirect:/";
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
