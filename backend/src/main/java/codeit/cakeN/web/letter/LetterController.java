package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.service.letter.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/letter")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;

    @GetMapping("/")
    public String BlankPage() {
        return "newletter/blank";
    }

    @GetMapping("/list")
    public String list() {
        // model.addAttribute("newletters", newletterService.list());
        return "newletter/list";
    }

    @GetMapping("/detail/{letter_id}")
    public String detail(@PathVariable Long letter_id, Model model) {
        model.addAttribute("newletter", letterService.detail(letter_id));
        return "newletter/detail";
    }

    @GetMapping("/register")
    public  String registerGet() {
        return "newletter/register";
    }

    @PostMapping("/register")
    public String registerPost(Letter letter) {
        letterService.register(letter);
        // return "redirect:/newletter/list";
        return "redirect:/newletter/";
    }

    @GetMapping("/update/{letter_id}")
    public String updateGet(@PathVariable Long letter_id, Model model) {
        model.addAttribute("newletter", letterService.detail(letter_id));
        return "newletter/update";
    }

    @PostMapping("/update")
    public String updatePost(Letter letter) {
        letterService.update(letter);
        return "redirect:/newletter/";
    }

    @GetMapping("/delete/{letter_id}")
    public String delete(@PathVariable Long letter_id) {
        letterService.delete(letter_id);
        return "redirect:/newletter/";
    }

    // 실험
    @GetMapping("/whynoletter")
    public String letterTest() {
        return "newletter/test";
    }

}
