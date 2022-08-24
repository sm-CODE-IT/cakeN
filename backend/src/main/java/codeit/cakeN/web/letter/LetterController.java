package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.service.letter.LetterService;
import codeit.cakeN.web.letter.dto.LetterRequestDto;
import codeit.cakeN.web.letter.dto.LetterUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/letter")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;


    /**
     * 레터링 목록 페이지
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("letters", letterService.list());
        return "letter/blank";
    }

    /**
     * 레터링 디테일 페이지
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("letter", letterService.detail(id));
        return "letter/detail";
    }

    /**
     * 레터링 등록 페이지
     * @return
     */
    @GetMapping("/register")
    public  String registerGet(@ModelAttribute("letterRequestDto") LetterRequestDto letterRequestDto) {
        return "letter/register";
    }

    /**
     * 레터링 등록 로직
     * @param letter
     * @return
     */
    @PostMapping("/register")
    public String registerPost(LetterRequestDto letter) {
        letterService.register(letter);
        return "redirect:/letter/";
    }

    /**
     * 레터링 수정 페이지
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") Long id, Model model) {
        Letter letterRequestDto = letterService.detail(id);
        model.addAttribute("letter", letterRequestDto);
        return "letter/update";
    }

    /**
     * 레터링 수정 로직
     * @param letter
     * @return
     */
    @PostMapping("/update")
    public String updatePost(LetterUpdateDto letter) {
        letterService.update(letter.getId(), letter);
        return "redirect:/letter/";
    }

    /**
     * 레터링 삭제
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        letterService.delete(id);
        return "redirect:/letter/";
    }

    // 실험
    @GetMapping("/whynoletter")
    public String letterTest() {
        return "letter/test";
    }

}