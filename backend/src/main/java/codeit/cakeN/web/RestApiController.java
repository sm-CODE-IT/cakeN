package codeit.cakeN.web;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final UserRepository userRepository;

    @GetMapping("/api/test")
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }
}
