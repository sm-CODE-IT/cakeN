package codeit.cakeN.service.user;

import codeit.cakeN.config.auth.dto.SecurityUser;
import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @return null이면 로그인 실패
     */
    public User login(String loginId, String password) {
        /*return userRepository.findByEmail(loginId)
                .filter(m -> passwordEncoder.matches(password, m.getPassword()))
                .orElse(null);*/


        Optional<User> findUser = userRepository.findByEmail(loginId);
        User user = findUser.get();   // Optional로 감싼 형태에서 꺼내기
        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setRole(Role.USER);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findByEmail(email);// 이메일로 사용자 찾기

        if (!findUser.isPresent())
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");

        User user = findUser.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleKey()));

        log.info("Success find user {}", findUser);

        // 특정 관리자 계정에만 ADMIN Role 부여
        if ("admin@admin.com".equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getKey()));
            user.setRole(Role.ADMIN);
        } else {
            // 그 외는 모두 USER Role 부여
            authorities.add(new SimpleGrantedAuthority(Role.USER.getKey()));
            user.setRole(Role.USER);
        }
        return new SecurityUser(user);
    }
}
