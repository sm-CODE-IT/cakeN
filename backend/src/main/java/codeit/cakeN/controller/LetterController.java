package codeit.cakeN.controller;

import codeit.cakeN.dto.LetterDto;
import codeit.cakeN.service.LetterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LetterController {

    private LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }


    @GetMapping("/letter")
    public String list() {
        return "letter/list2";
    }

    @GetMapping("/letter/post")
    public String post() {
        return "letter/post2";
    }

    @PostMapping("/letter/post")
    public String write(LetterDto letterDto) {
        letterService.savePost(letterDto);
        return "redirect:/";
    }
}
