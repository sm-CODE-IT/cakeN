package codeit.cakeN.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository {

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public User save(User user) {
        user.setUserId(++sequence);
        log.info("save: user={}", user);
        store.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        /*List<User> all = findAll();
        for (User user : all) {
            if (user.getEmail().equals(email))
                return Optional.of(user);
        }
        return Optional.empty();*/

        return findAll().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void cleanStore() {
        store.clear();
    }
}
