/*
package codeit.cakeN.service.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import codeit.cakeN.domain.letter.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

// DB Test를 위한 초기 실행 지정
@RequiredArgsConstructor
@Service
public class LetterDBInit implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        this.letterRepository.deleteAll();

        Letter birthday = new Letter("생일 축하해요", Tag.BIRTHDAY);
        Letter birthday2 = new Letter("생일 축하합니다~!!", Tag.BIRTHDAY);
        Letter birthday3 = new Letter("생일 333", Tag.BIRTHDAY);

        Letter love1 = new Letter("100일 축", Tag.ANNIVERSARY);
        Letter love2 = new Letter("200일 축", Tag.ANNIVERSARY);

        List<Letter> letterList = Arrays.asList(birthday, birthday2, birthday3, love1, love2);
        this.letterRepository.saveAll(letterList);
        System.out.println(this.letterRepository.findAll());
    }
}
*/
