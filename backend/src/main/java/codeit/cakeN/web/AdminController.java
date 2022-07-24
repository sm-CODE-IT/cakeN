package codeit.cakeN.web;

import codeit.cakeN.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    /////// 관리자 페이지 /////// -> 예시
    @GetMapping("/admin/")
    public String adminUser(Model model) {
        model.addAttribute("user", new User());
        return "user/admin";
    }
}
