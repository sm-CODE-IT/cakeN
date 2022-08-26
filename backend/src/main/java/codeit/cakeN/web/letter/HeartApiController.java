package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.service.letter.HeartService;
import codeit.cakeN.web.letter.dto.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HeartApiController {

    private final HeartService heartService;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @PostMapping("/heart")
    @ResponseStatus(HttpStatus.OK)
    public Heart heart(HeartDto heartDto, @AuthenticationPrincipal User formUser) throws Exception {
        /*codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);
        heartDto.setUserId(user.getUserId());*/
        return heartService.heart(heartDto);
    }

    @DeleteMapping("/heart")
    @ResponseStatus(HttpStatus.OK)
    public HeartDto unHeart(HeartDto heartDto) throws Exception {
        heartService.unheart(heartDto);
        return heartDto;
    }
}
