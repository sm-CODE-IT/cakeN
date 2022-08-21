package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.letter.LetterException;
import codeit.cakeN.service.letter.HeartService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HeartController {

    private final HeartService heartService;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @PostMapping("/heart")
    public ResponseEntity<Heart> heart(@RequestBody HeartDto heartDto, @AuthenticationPrincipal User formUser) throws Exception {
        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);
        heartDto.setUserId(user.getUserId());
        heartService.heart(heartDto);
        return ResponseEntity.ok(heartDto.toEntity());
    }

    @DeleteMapping("/heart")
    public ResponseEntity<Heart> unHeart(@RequestBody HeartDto heartDto) throws Exception {
        heartService.unheart(heartDto);
        return ResponseEntity.ok(heartDto.toEntity());
    }
}
