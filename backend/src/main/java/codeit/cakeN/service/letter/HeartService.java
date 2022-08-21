package codeit.cakeN.service.letter;

import codeit.cakeN.domain.letter.Heart;
import codeit.cakeN.domain.letter.HeartRepository;
import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.letter.LetterException;
import codeit.cakeN.exception.letter.LetterExceptionType;
import codeit.cakeN.web.letter.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final LetterRepository letterRepository;

    public void heart(HeartDto heartDto) throws Exception {

        // 이미 좋아요한 레터링은 예외로 처리
        if (findHeartWithUserAndLetter(heartDto).isPresent()) {
            throw new LetterException(LetterExceptionType.ALREADY_HEART_LETTER);
        }

        Heart heart = heartDto.toEntity();
        heartRepository.save(heart);

        updateHeartCount(heartDto.getLetterId(), 1);

    }

    public void unheart(HeartDto heartDto) throws Exception {
        Optional<Heart> heartOpt = findHeartWithUserAndLetter(heartDto);

        if (heartOpt.isEmpty()) {
            throw new LetterException(LetterExceptionType.NOT_FOUND_HEART);
        }

        heartRepository.delete(heartOpt.get());

        updateHeartCount(heartDto.getLetterId(), -1);
    }

    public Optional<Heart> findHeartWithUserAndLetter(HeartDto heartDto) {
        return heartRepository.findHeartByUserAndLetter(userRepository.findById(heartDto.getUserId()).get(), letterRepository.findById(heartDto.getLetterId()).get());
    }

    public void updateHeartCount(Long letterId, Integer plusOrMinus) throws Exception {
        Optional<Letter> letterOpt = letterRepository.findById(letterId);
        if (letterOpt.isEmpty()) {
            throw new LetterException(LetterExceptionType.NOT_FOUND_LETTER);
        }

        Integer heartCount = letterOpt.get().getHearts();
        heartCount += plusOrMinus;
        letterOpt.get().setHearts(heartCount);
    }
}