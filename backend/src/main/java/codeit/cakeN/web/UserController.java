package codeit.cakeN.web;


import codeit.cakeN.config.auth.SecurityUtil;

import codeit.cakeN.service.user.LoginService;
import codeit.cakeN.web.dto.UserDeleteDto;
import codeit.cakeN.web.dto.UserLoginRequestDto;
import codeit.cakeN.web.dto.UserRequestDto;
import codeit.cakeN.service.user.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

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
    public String delete() {
        return "user/deleteForm";
    }

    /**
     * 회원탈퇴 로직
     * @param userDeleteDto
     * @throws Exception
     */
    @DeleteMapping("/deleteUser")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@Valid @RequestBody UserDeleteDto userDeleteDto) throws Exception {
        userService.deleteUser(userDeleteDto.checkPw(), SecurityUtil.getLoginUser());
    }


    /**
     * 로그인 페이지
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userLoginRequestDto", new UserLoginRequestDto());

        return "user/login";
    }


    /**
     * 마이페이지 (개인정보 조회)
     */
    @GetMapping("/mypage")
    public String myPage(Model model) {
        return "user/mypage";
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
