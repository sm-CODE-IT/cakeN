package codeit.cakeN.service.letter;

import codeit.cakeN.domain.repository.letter.LetterRepository;
import codeit.cakeN.web.dto.LetterDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LetterService {
    private LetterRepository letterRepository;

    public LetterService(LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    @Transactional
    public Long savePost(LetterDto letterDto) {
        return letterRepository.save(letterDto.toEntity()).getLetter_id();
    }
}
// 카테고리 별 파일 정리