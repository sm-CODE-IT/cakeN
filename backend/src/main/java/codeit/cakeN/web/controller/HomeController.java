package codeit.cakeN.web.controller;


import codeit.cakeN.config.auth.LoginUser;
import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.CustomUserDetails;

import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.exception.user.UserExceptionType;
import codeit.cakeN.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession httpSession;
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal User formUser) throws Exception {

        // userName에는 유저의 닉네임이 매핑된다.

        /**
         * 소셜 로그인으로 접근한 유저
         */
        SecurityUser user = (SecurityUser) httpSession.getAttribute("user");


        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        /**
         * Form Login으로 접근한 유저
         */

        if (formUser != null) {
            System.out.println(formUser);
            codeit.cakeN.domain.user.User findUser = userRepository.findByEmail(formUser.getUsername()).get();
            System.out.println(findUser);
            model.addAttribute("userName", findUser.getNickname());
        }


        return "home2";
    }

}
