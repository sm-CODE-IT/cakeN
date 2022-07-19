package codeit.cakeN.service.user;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    /**
     *
     * @return null이면 로그인 실패
     */
    public User login(String loginId, String password) {
        /*Optional<User> findUser = userRepository.findByEmail(loginId);
        User user = findUser.get();   // Optional로 감싼 형태에서 꺼내기
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }*/

        return userRepository.findByEmail(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
