package codeit.cakeN.service.contest;

import codeit.cakeN.domain.contest.ContestRepository;
import codeit.cakeN.domain.contest.Scrap;
import codeit.cakeN.domain.contest.ScrapRepository;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.CustomException;
import codeit.cakeN.web.contest.ScrapDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private User user;

    public void scrap(ScrapDto scrapDto) throws IOException {

        // 이미 스크랩한 게시물은 예외로 처리
        if (findScrapWithUserAndContest(scrapDto).isPresent()) {
            throw new IOException();
        }

        Scrap scrap = Scrap.builder()
                .contest(contestRepository.findById(scrapDto.getContestId()).get())
                .user(userRepository.findById(scrapDto.getUserId()).get())
                .build();
        scrapRepository.save(scrap);

        updateScrapCount(scrapDto.getContestId(), 1);
    }

    public void unscrap(ScrapDto scrapDto) throws IOException {
        Optional<Scrap> scrapOpt = findScrapWithUserAndContest(scrapDto);

        if (scrapOpt.isEmpty()) {
            throw new IOException();
        }

        scrapRepository.delete(scrapOpt.get());

        updateScrapCount(scrapDto.getContestId(), -1);
    }

    public Optional<Scrap> findScrapWithUserAndContest(ScrapDto scrapDto) {
        return scrapRepository.findScrapByUserAndContest(user, contestRepository.findById(scrapDto.getContestId()).get());
    }

    public void updateScrapCount(Long contestId, Integer plusOrMinus) throws IOException {

    }

}
