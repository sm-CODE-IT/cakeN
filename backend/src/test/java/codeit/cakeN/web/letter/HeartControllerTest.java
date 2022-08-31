package codeit.cakeN.web.letter;

import codeit.cakeN.domain.letter.Letter;
import codeit.cakeN.domain.letter.LetterRepository;
import codeit.cakeN.domain.letter.Tag;
import codeit.cakeN.domain.user.Role;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
//@Import(JpaConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HeartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Long> input = new HashMap<>();   // heartRepository 테스트용


    @BeforeEach
    void setBody() {
        User user = User.builder()
                .email("jun020216@naver.com")
                .pw("****")
                .intro("안녕")
                .image("11.jpg")
                .nickname("jun")
                .role(Role.USER)
                .build();
        userRepository.save(user);

        Letter letter = Letter.builder()
                .content("뇽안")
                .tag(Tag.BIRTHDAY)
                .build();
        letterRepository.save(letter);

        input.put("letterId", 1L);
        input.put("userId", 1L);
    }

    @Test
    @Order(100)
    @DisplayName("좋아요 테스트 - 성공")
    public void doHeart() throws Exception {
        mockMvc
                .perform(post("/api/heart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))

                .andExpect(status().isCreated());
    }

    @Test
    @Order(101)
    @DisplayName("좋아요 테스트 - 실패 (이미 좋아요 한 레터링)")
    public void doHeartFailDuplicate() throws Exception {
        mockMvc
                .perform(post("/api/heart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))

                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("ALREADY_HEART"))
                .andExpect(jsonPath("$.message").value("이미 좋아요 한 레터링입니다."));
    }

    @Test
    @Order(200)
    @DisplayName("좋아요 취소 테스트 - 성공")
    public void unHeart() throws Exception {
        mockMvc
                .perform(delete("/api/heart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }
}