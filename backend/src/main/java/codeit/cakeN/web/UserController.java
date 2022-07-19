package codeit.cakeN.web;

import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.web.dto.UserRequestDto;
import codeit.cakeN.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/new")
    public String createUserForm(@ModelAttribute("user") User user) {
        return "user/createUserForm";
    }

    @PostMapping("/new")
    public String createUser(@Validated @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/createUserForm";
        }

        userRepository.save(user);
        return "redirect:/";
    }



    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/login";
    }


    /*
     * rest API
     */

    /*// Create
    //*public User createUser(@RequestBody UserRequestDto requestDto) {
        User user = new User(requestDto);
        return userRepository.save(user);
    }*//*

    // Read (전체)
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Read (개별)
    @GetMapping("/api/users/{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return Optional.of(userRepository.findById(userId));
    }

    // Update -> Patch / Put
    @PutMapping("/api/users/{userId}")
    public Long updateUser(@PathVariable Long userId, @RequestBody UserRequestDto requestDto) {
        return userService.update(userId, requestDto);
    }

    // Delete
    @DeleteMapping("/api/users/{userId}")
    public Long deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return userId;
    }*/
}