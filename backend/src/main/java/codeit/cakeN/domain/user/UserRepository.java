package codeit.cakeN.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*
    // User CRUD
    User save(User user);
    Optional<User> findById(Long id);  // id로 유저 찾기
    Optional<User> findByEmail(String email);   // 이메일로 유저 찾기
    List<User> findAll();   // 모든 유저 목록 조회
    */

    static final String UPDATE_MEMBER_LAST_LOGIN = "UPDATE Member SET LAST_LOGIN_TIME = :lastLoginTime WHERE EMAIL = :emil";

    @Transactional
    @Modifying
    @Query(value = UPDATE_MEMBER_LAST_LOGIN, nativeQuery = true)
    public int updateUserLastLogin(@Param("email") String email, @Param("lastLoginTime") LocalDateTime lastLoginTime);
    public User findByEmail(String email);
}
