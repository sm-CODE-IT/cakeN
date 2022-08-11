package codeit.cakeN.NewLetter.controller;

import codeit.cakeN.NewLetter.domain.NewLetter;
import codeit.cakeN.NewLetter.service.NewLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newletter")
@RequiredArgsConstructor
public class NewLetterController {

    private final NewLetterService newletterService;

    @GetMapping("/")
    public String BlankPage() {
        return "newletter/blank";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("newletters", newletterService.list());
        return "newletter/list";
    }

    @GetMapping("/detail/{letter_id}")
    public String detail(@PathVariable int letter_id, Model model) {
        model.addAttribute("newletter", newletterService.detail(letter_id));
        return "newletter/detail";
    }

    @GetMapping("/register")
    public  String registerGet() {
        return "newletter/register";
    }

    @PostMapping("/register")
    public String registerPost(NewLetter newletter) {
        newletterService.register(newletter);
        // return "redirect:/newletter/list";
        return "redirect:/newletter/";
    }

    @GetMapping("/update/{letter_id}")
    public String updateGet(@PathVariable int letter_id, Model model) {
        model.addAttribute("newletter", newletterService.detail(letter_id));
        return "newletter/update";
    }

    @PostMapping("/update")
    public String updatePost(NewLetter newletter) {
        newletterService.update(newletter);
        return "redirect:/newletter/";
    }

    @GetMapping("/delete/{letter_id}")
    public String delete(@PathVariable int letter_id) {
        newletterService.delete(letter_id);
        return "redirect:/newletter/";
    }

    // 실험
    @GetMapping("/whynoletter")
    public String letterTest() {
        return "newletter/test";
    }

}
