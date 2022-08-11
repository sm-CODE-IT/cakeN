package codeit.cakeN.web.contest;

import codeit.cakeN.domain.contest.Contest;
import codeit.cakeN.service.contest.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contest")
@RequiredArgsConstructor
public class ContestController {

    public final ContestService contestService;

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
    public String detail(@PathVariable long postId, Model model) {
        model.addAttribute("contest", contestService.detail(postId));
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
