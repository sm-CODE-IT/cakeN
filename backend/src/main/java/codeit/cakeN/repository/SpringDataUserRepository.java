package codeit.cakeN.repository;

import codeit.cakeN.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<User, Long>, UserRepository {
    
    // JPA(JpaRepository)로 CRUD 메소드 자동 생성
    @Override
    Optional<User> findByEmail(String email);
}
