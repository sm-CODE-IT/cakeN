package codeit.cakeN.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);   // 이메일로 유저 찾기
    List<User> findAll();   // 모든 유저 목록 조회

    // User CRUD (JPA 사용 x)
    /* User save(User user);
    User findById(Long id);  // id로 유저 찾기*/
}
