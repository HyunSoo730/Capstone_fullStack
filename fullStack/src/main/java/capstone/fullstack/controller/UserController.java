package capstone.fullstack.controller;

import capstone.fullstack.domain.User;
import capstone.fullstack.dto.KakaoProfile;
import capstone.fullstack.dto.OauthToken;
import capstone.fullstack.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //프론트에서 인가코드 받아오는 url
//    @PostMapping("/user/login")
//    public User login(@RequestBody String accessToken) {
//        //프론트에서 넘겨주는 accessToken 카카오 회원 정보 DB에 저장
//        User user = userService.save(accessToken);
//        return user;
//    }

    @GetMapping("/auth/kakao/callback")
    public User kakaoCallback(@RequestParam("code") String code) {
        //POST방식으로 key=value 데이터를 요청(카카오 쪽으로)
        OauthToken oauthToken = userService.getAccessToken(code);
        User user = userService.save(oauthToken.getAccess_token());
        return user;
    }
}
