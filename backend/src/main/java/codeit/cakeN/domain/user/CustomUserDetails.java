package codeit.cakeN.domain.user;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;
    private static final long serialVersionID = 1L;

    @Autowired
    public CustomUserDetails(User user) {
        this.user = user;
    }

    // 계정(해당 유저)이 가진 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "계정별 등록 권한";
        });

        return collectors;
    }

    // 비밀번호 가져오기
    @Override
    public String getPassword() {
        return user.getPw();
    }

    // pk 값 가져오기
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * 계정 만료 여부
     * true : 만료 X
     * false : 만료 O
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자 활성화 여부
     * true : 활성화
     * false :
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
