package capstone.fullstack.controller;

import capstone.fullstack.domain.user.User;
import capstone.fullstack.dto.OauthToken;
import capstone.fullstack.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
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
     * 사용자 정보 반환. 카카오 정보 + 닉네임
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
