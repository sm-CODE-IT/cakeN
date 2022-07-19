package codeit.cakeN.service.user;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Long save(UserRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUserId();
    }

    @Transactional
    public void encryptPassword(String userPw) {
        User user = new User();
        String enPw = passwordEncoder.encode(userPw);
        user.setPw(enPw);
        userRepository.save(user);
    }

    /*@Transactional
    public Long update(Long id, UserRequestDto requestDto) {
        User user1 = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

        user1.update(requestDto);

        return user1.getUserId();
    }*/

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);   // 이메일로 사용자 찾기

        if (user == null)
            throw new UsernameNotFoundException("Not Found account.");

        return user;
    }*/
}
