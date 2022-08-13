package codeit.cakeN.domain.contest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ContestRepository extends JpaRepository<Contest, Long> {
/*
    *//**
     * 스크랩 추가
     * @param contestId
     *//*
    @Modifying
    @Query(value = "update Contest contest set contest.scrapCount = contest.scrapCount + 1 where contest.id = :contest_id")
    void plusScrap(@Param("contestId") Long contestId);

    *//**
     * 스크랩 삭제
     * @param contestId
     *//*
    @Modifying
    @Query(value = "update Contest contest set contest.scrapCount = contest.scrapCount - 1 where contest.id = :contest_id")
    void minusScrap(@Param("contestId") Long contestId);*/
}
