package codeit.cakeN.web;

import codeit.cakeN.config.auth.LoginUser;
import codeit.cakeN.config.auth.dto.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "home";
    }

}
