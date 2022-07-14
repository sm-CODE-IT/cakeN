package codeit.cakeN.repository;

import codeit.cakeN.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);  // id로 유저 찾기
    Optional<User> findByEmail(String email);   // 이메일로 유저 찾기
    List<User> findAll();   // 모든 유저 목록 조회
}
