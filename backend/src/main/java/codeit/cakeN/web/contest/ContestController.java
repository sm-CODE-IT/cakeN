package codeit.cakeN.web.contest;

import codeit.cakeN.domain.contest.Contest;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.service.contest.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@Controller
@RequestMapping("/contest")
@RequiredArgsConstructor
public class ContestController {

    public final ContestService contestService;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String BlankPage() {
        return "contest/blank";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("contest", contestService.list());
        return "contest/list";
    }

    @GetMapping("/detail/{postId}")
    public String detail(@PathVariable long postId, Model model, @AuthenticationPrincipal User formUser) {
        model.addAttribute("contest", contestService.detail(postId));

        boolean scrap = false;   // 로그인 X 유저는 항상 false

        // 현재 접속한 유저 
        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);
        if (user != null) {
            Long userId = user.getUserId();

            // 현재 로그인한 유저가 해당 게시물을 스크랩 했는지 여부 체크
//            scrap = contestService.findScrapContest(postId, userId);
        }
        model.addAttribute("scrap", scrap);

        return "contest/detail";
    }

    @GetMapping("/register")
    public String registerGet() {
        return "contest/register";
    }

    @PostMapping("/register")
    public String registerPost(Contest contest) {
        contestService.register(contest);
        return "redirect:/contest/list";
    }

    @GetMapping("/update/{postId}")
    public String updateGet(@PathVariable int postId, Model model) {
        model.addAttribute("contest", contestService.detail(postId));
        return "contest/update";
    }

    @PostMapping("/update")
    public String updatePost(Contest contest) {
        contestService.update(contest);
        return "redirect:/contest/list";
    }

    @GetMapping("/delete/{idx}")
    public String delete(@PathVariable int postId) {
        contestService.delete(postId);
        return "redirect:/contest/list";
    }



}
