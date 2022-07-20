package codeit.cakeN.service.user;

import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void save(UserRequestDto requestDto) {
        String enPw = passwordEncoder.encode(requestDto.toEntity().getPassword());
        requestDto.setPw(enPw);
        userRepository.save(requestDto.toEntity());
    }

    /*@Transactional
    public void encryptPassword(String userPw) {
        User user = new User();
        String enPw = passwordEncoder.encode(userPw);
        user.setPw(enPw);
    }*/

    /*@Transactional
    public Long update(Long id, UserRequestDto requestDto) {
        User user1 = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

        user1.update(requestDto);

        return user1.getUserId();
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findByEmail(email);// 이메일로 사용자 찾기

        if (!findUser.isPresent())
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");

        log.info("Success find user {}", findUser);

        return new SecurityUser(findUser.get());
    }
}
