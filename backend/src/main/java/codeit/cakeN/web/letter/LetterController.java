package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.service.letter.LetterService;
import codeit.cakeN.web.letter.dto.LetterRequestDto;
import codeit.cakeN.web.letter.dto.LetterUpdateDto;
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
    public String list(Model model) {
        model.addAttribute("letters", letterService.list());
        return "letter/blank";
    }

    @GetMapping("/detail/{letter_id}")
    public String detail(@PathVariable Long letter_id, Model model) {
        model.addAttribute("letter", letterService.detail(letter_id));
        return "letter/detail";
    }

    @GetMapping("/register")
    public  String registerGet() {
        return "letter/register";
    }

    @PostMapping("/register")
    public String registerPost(LetterRequestDto letter) {
        letterService.register(letter);
        // return "redirect:/newletter/list";
        return "redirect:/letter/";
    }

    @GetMapping("/update/{letter_id}")
    public String updateGet(@PathVariable Long letter_id, Model model) {
        model.addAttribute("letter", letterService.detail(letter_id));
        return "letter/update";
    }

    @PostMapping("/update")
    public String updatePost(LetterUpdateDto letter) {
        letterService.update(letter.getId(), letter);
        return "redirect:/letter/";
    }

    @GetMapping("/delete/{letter_id}")
    public String delete(@PathVariable Long letter_id) {
        letterService.delete(letter_id);
        return "redirect:/letter/";
    }

    // 실험
    @GetMapping("/whynoletter")
    public String letterTest() {
        return "letter/test";
    }

}
