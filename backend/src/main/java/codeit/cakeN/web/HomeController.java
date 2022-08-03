package codeit.cakeN.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
