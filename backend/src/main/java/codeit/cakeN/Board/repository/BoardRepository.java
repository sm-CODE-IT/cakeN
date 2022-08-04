package codeit.cakeN.Board.repository;

import codeit.cakeN.Board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}