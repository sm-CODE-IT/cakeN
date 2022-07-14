package codeit.cakeN.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // User CRUD
    User save(User user);
    Optional<User> findById(Long id);  // id로 유저 찾기
    Optional<User> findByEmail(String email);   // 이메일로 유저 찾기
    List<User> findAll();   // 모든 유저 목록 조회
}
