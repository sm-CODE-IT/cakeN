package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.letter.HeartRepository;
import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.Tag;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.service.letter.LetterService;
import codeit.cakeN.web.letter.dto.LetterRequestDto;
import codeit.cakeN.web.letter.dto.LetterUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@Controller
@RequestMapping("/letter")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;
    private final UserRepository userRepository;
    private final HeartRepository heartRepository;
    private final HttpSession httpSession;


    /**
     * 레터링 목록 페이지
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model, @AuthenticationPrincipal User formUser) {
        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);
        List<Heart> heartList = heartRepository.findByUser(user).get();
        List<Letter> birthday = new ArrayList<>();
        for (Heart h : heartList) {
            if (h.getLetter().getTag() == Tag.BIRTHDAY)
                birthday.add(h.getLetter());
        }

        model.addAttribute("user", user);
        model.addAttribute("letters", letterService.list());
        model.addAttribute("hearts", heartList);
        return "letter/list";
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
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable("id") Long id, LetterUpdateDto letter) {
        letterService.update(id, letter);
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