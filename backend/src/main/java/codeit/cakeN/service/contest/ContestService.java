package codeit.cakeN.service.contest;

import codeit.cakeN.domain.contest.Contest;
import codeit.cakeN.domain.contest.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;

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

}
