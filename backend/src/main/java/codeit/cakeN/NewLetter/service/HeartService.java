package codeit.cakeN.NewLetter.service;

import codeit.cakeN.NewLetter.repository.HeartRepository;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;

    /*
    @Transactional
    public void heart(int letter_id, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        heartRepository.unLikes(letter_id, user);   // ? 가능?
    }
     */
}
