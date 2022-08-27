package codeit.cakeN.config.auth;

import codeit.cakeN.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

// 사용자 정보를 받아 JWT를 생성하는 클래스
@Slf4j
@Service
public class TokenProvider {

    private static final String SECRET_KEY = "NMA8JPctFuna59f5";

    /**
     * JWT 라이브러리를 이용해 JWT 토큰을 생성
     * @param user
     * @return
     */
    public String create(User user) {
        // 기한은 지금부터 1일로 설정
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS)
        );

        /*
        JWT Token 형식
        { // Header
            "typ": "JWT",    // 토큰의 타입
            "alg": "HS512"   // 토큰의 서명을 발행하는 데 사용된 해시 알고리즘 종류
        }.
        { // Payload
            "sub": "402889021380912i23",   // 토큰의 주인(유일한 식별자 = 사용자)
            "iss": "demo app",     // 토큰의 발행 주체 (= cakeN 서비스)
            "iat": 15892032,       // 토큰 발행 시간/날짜 (issued at)
            "exp": 15963293        // 토큰 만료 시간 (expiration)
        }.
        { // Signature - SECRET_KEY를 이용해 서명한 부분
            NDJDSDsadjaslkdjasldjlkasjdlka
        }
        */

        // JWT Token 생성
        return Jwts.builder()
                // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // payload에 들어갈 내용
                .setSubject(String.valueOf(user.getUserId()))    // sub
                .setIssuer("cakeN")   // iss
                .setIssuedAt(new Date())   // iat
                .setExpiration(expiryDate)   // exp
                .compact();
    }

    /**
     * 토큰 디코딩 및 파싱 -> 토큰의 위조여부 확인
     *
     * parseClaimsJws 메서드가 Base64로 디코딩 및 파싱
     * 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후, token의 서명과 비교
     * 위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
     * 그 중 우리는 userId가 필요하머로, getBody를 부른다.
     * @param token
     * @return
     */
    public String validateAndGetUserId(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        
        return claims.getSubject();
    }
}
