package codeit.cakeN.web.controller;

import codeit.cakeN.config.auth.LoginUser;
import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    /*@GetMapping("/")
    public String home(Model model, @LoginUser SecurityUser user, @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        if (userDetails != null) {
            model.addAttribute("userName", userDetails.getUsername());
        }
        return "home";
    }*/

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
