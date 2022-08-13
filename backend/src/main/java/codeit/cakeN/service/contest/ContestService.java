package codeit.cakeN.service.contest;

import codeit.cakeN.domain.contest.Contest;
import codeit.cakeN.domain.contest.ContestRepository;
import codeit.cakeN.domain.contest.ContestScrap;
import codeit.cakeN.domain.contest.ContestScrapRepository;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final ContestScrapRepository contestScrapRepository;
    private final UserRepository userRepository;

    public void register(Contest contest) {
        contestRepository.save(contest);
    }

    public List<Contest> list() {
        return contestRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));
    }

    public Contest detail(long postId) {
        return contestRepository.findById(postId).orElse(null);
    }

    public void update(Contest contest) {
        contestRepository.save(contest);
    }

    public void delete(long postId) {
        contestRepository.deleteById(postId);
    }


    /*public boolean findScrapContest(Long contestId, Long userId) {

        // 저장된 DTO가 있다면 true, 없다면 false을 반환
        return contestScrapRepository.existsByContest_IdAndUser_Id(contestId, userId);
    }*/

//    @Transactional
    /*public boolean saveScrapContest(Long contestId, Long userId) {

        boolean findScrap = contestScrapRepository.existsByContest_IdAndUser_Id(contestId, userId);

        System.out.println(findScrap);

        if (findScrap) {
            User user = userRepository.findById(userId).get();
            Contest contest = contestRepository.findById(contestId).get();

            ContestScrap contestScrap = ContestScrap.toContestScrap(user, contest);
            contestScrapRepository.save(contestScrap);
//            contestRepository.plusScrap(contestId);
            return true;
        } else {
            contestScrapRepository.deleteByContest_IdAndUser_Id(contestId, userId);
//            contestRepository.minusScrap(contestId);
            return false;
        }
    }*/

}
