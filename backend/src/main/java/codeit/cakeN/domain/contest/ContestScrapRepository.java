package codeit.cakeN.domain.contest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContestScrapRepository extends JpaRepository<ContestScrap, Long> {

    /*boolean existsByContest_IdAndUser_Id(Long contestId, Long userId);
    void deleteByContest_IdAndUser_Id(Long contestId, Long userId);*/
}