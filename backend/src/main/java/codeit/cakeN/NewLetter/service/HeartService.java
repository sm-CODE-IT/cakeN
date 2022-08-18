package codeit.cakeN.NewLetter.service;

import codeit.cakeN.NewLetter.controller.HeartDto;
import codeit.cakeN.NewLetter.domain.Heart;
import codeit.cakeN.NewLetter.repository.HeartRepository;
import codeit.cakeN.domain.user.User;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;

    // private final CampaignRepository campaignRepository;
    // private final RestHighLevelClient elasticsearchClient;
    // private final JwtTokenProvider jwtTokenProvider;
    private User user;

    public void heart(HeartDto heartDto, String jwtToken) throws IOException {
        validateToken(heartDto, jwtToken);

        // 이미 좋아요 된 캠페인일 경우 409 에러
        if (findHeartWithUserAndCampaignId(heartDto).isPresent()) {
            throw new CustomException(ALREADY_HEARTED);
        }

        Heart heart = Heart.builder()
                .campaignId(heartDto.getCampaignId())
                .user(userRepository.findUserById(heartDto.getUserId()).get())
                .build();
        heartRepository.save(heart);

        updateHeartCount(heartDto.getCampaignId(), 1);

    }

    public void unHeart(HeartDto heartDto, String jwtToken) throws IOException {
        validateToken(heartDto, jwtToken);

        Optional<Heart> heartOpt = findHeartWithUserAndCampaignId(heartDto);

        if (heartOpt.isEmpty()) {
            throw new CustomException(HEART_NOT_FOUND);
        }

        heartRepository.delete(heartOpt.get());

        updateHeartCount(heartDto.getCampaignId(), -1);
    }

    public void validateToken(HeartDto heartDto, String jwtToken) {
        // 생략 ... 유효한 토큰인지 검증하는 부분
    }

    public Optional<Heart> findHeartWithUserAndCampaignId(HeartDto heartDto) {
        return heartRepository
                .findHeartByUserAndCampaignId(user, heartDto.getCampaignId());
    }

    public void updateHeartCount(String campaignId, Integer plusOrMinus) throws IOException {
        // Elasticsearch 업데이트 하는 부분
        // 필요한 분들이 별로 없을것같아 생략합니다. 필요하시면 맨아래 깃헙 링크 참고하세요!
        // MySQL만으로 구현하실때에는 JPA로 업데이트 하는 코드 넣어주세요~!
    }



    /*
    @Transactional
    public void heart(int letter_id, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        heartRepository.unLikes(letter_id, user);   // ? 가능?
    }
     */
}
