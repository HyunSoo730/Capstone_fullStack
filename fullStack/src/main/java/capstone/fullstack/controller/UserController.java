package capstone.fullstack.controller;

import capstone.fullstack.domain.User;
import capstone.fullstack.dto.KakaoProfile;
import capstone.fullstack.dto.OauthToken;
import capstone.fullstack.jwt.JwtProperties;
import capstone.fullstack.service.UserService;
import capstone.fullstack.util.JwtTokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam("code") String code) {
        //POST방식으로 key=value 데이터를 요청(카카오 쪽으로)
        OauthToken oauthToken = userService.getAccessToken(code);
//        User user = userService.save(oauthToken.getAccess_token());
        String jwtToken = userService.save(oauthToken.getAccess_token());
        //발급받은 access token으로 카카오 회원 정보 DB 저장 후 JWT 생성

        return jwtToken;
    }

    /**
     * 사용자 정보 요청오면 토큰을 복호화해서 정보 가져온 후에 반환.
     */
    @PostMapping("/auth/kakao/token")
    public User userInfo(@RequestHeader("Authorization") String token) {
        //JWT 토큰 검증
        log.info("프론트로부터 들어오는 token, {}", token);
        User user = userService.validateToken(token);
        return user;
    }


}
