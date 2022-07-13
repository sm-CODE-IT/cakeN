package codeit.cakeN.repository;

import codeit.cakeN.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<User, Long>, UserRepository {

    @Override
    Optional<User> findByName(String name);
}
