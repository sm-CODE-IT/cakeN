package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.service.letter.HeartService;
import codeit.cakeN.web.letter.dto.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/letter")
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/heart")
    public String heart(HeartDto heartDto) throws Exception {
        /*codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);
        heartDto.setUserId(user.getUserId());*/
        heartService.heart(heartDto);
        return "redirect:/letter/";
    }

    @GetMapping("/unheart")
    public String unHeart(HeartDto heartDto) throws Exception {
        heartService.unheart(heartDto);
        return "redirect:/letter/";
    }
}
