package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.letter.LetterException;
import codeit.cakeN.exception.letter.LetterExceptionType;
import codeit.cakeN.exception.user.UserException;
import codeit.cakeN.service.letter.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.List;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LetterApiController {

    private final LetterService letterService;
    private final LetterRepository letterRepository;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    /**
     * Letter 등록
     * @param letter
     * @return
     */
    @PostMapping("/letter")
    public ResponseEntity<Letter> registerLetter(Letter letter, @AuthenticationPrincipal User formUser) {
        letterService.register(letter);

        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);

        /* if (user != null) {
            user.getLetterList().add(letter);
        }*/

        return ResponseEntity.ok(letter);
    }

    /**
     * Letter List 가져오기
     * @return
     */
    @GetMapping("/letter")
    public List<Letter> letterList() {
        return this.letterRepository.findAll();
    }

    /**
     * Letter Detail 페이지
     * @param id
     * @return
     */
    @GetMapping("/letter/{id}")
    public ResponseEntity<Letter> letterDetail(@PathVariable("id") Long id) {
        Letter letter = letterService.detail(id);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity(letter, header, HttpStatus.OK);
    }

    /**
     * Letter 정보 수정
     * @param id
     * @return
     */
    @PutMapping("/letter/{id}")
    public ResponseEntity<Letter> updateLetter(@PathVariable("id") Long id) throws LetterException {
        Letter letter = letterRepository.findById(id).orElseThrow(
                () -> new LetterException(LetterExceptionType.NOT_FOUND_LETTER)
        );
        letterService.update(letter);

        return ResponseEntity.ok().build();
    }

    /**
     * Letter 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/letter/{id}")
    public ResponseEntity deleteLetter(@PathVariable("id") Long id) {

        try {
            letterService.delete(id);
        } catch (LetterException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
