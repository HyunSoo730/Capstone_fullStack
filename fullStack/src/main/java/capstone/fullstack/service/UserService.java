package capstone.fullstack.service;

import capstone.fullstack.domain.User;
import capstone.fullstack.dto.KakaoProfile;
import capstone.fullstack.dto.OauthToken;
import capstone.fullstack.jwt.JwtProperties;
import capstone.fullstack.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Value("${kakao.api}")
    private String kakaoApiKey;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    public String saveUserAndGetToken(String token) {

        KakaoProfile profile = findProfile(token);

        User user = userRepository.findByKakaoEmail(profile.getKakao_account().getEmail());

        if (user == null) {
            user = User.builder()
                    .kakaoId(profile.getId())
                    .kakaoProfileImg(profile.getKakao_account().getProfile().getProfile_image_url())
                    .kakaoNickname(profile.getKakao_account().getProfile().getNickname())
                    .kakaoEmail(profile.getKakao_account().getEmail()).build();

            userRepository.save(user);
        }


//        return user;
        return createToken(user);
    }

    public String createToken(User user) { //(2-1)

        //(2-2)
        String jwtToken = JWT.create()
                //(2-3)
                .withSubject(user.getKakaoEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))

                //(2-4)
                .withClaim("id", user.getUserId())
                .withClaim("nickname", user.getKakaoNickname())

                //(2-5)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken; //(2-6)
    }


    public KakaoProfile findProfile(String token) {

        //(1-2)
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + token); //(1-4)
        headers2.add("Authorization", "Bearer " + token);
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2); //body 데이터와 헤더값 가짐.

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );
        //access token 받고 토큰으로 회원 정보까지 조회해서 결과가 response2에 담김.
        log.info("사용자 정보 조회, {}", response2.getBody());

        //ObjectMapper를 통해 객체로 변환
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //user 오브젝트 : username, password, email
        log.info("카카오 아이디 {}", kakaoProfile.getId());
        log.info("카카오 이메일 {}", kakaoProfile.getKakao_account().getEmail());
        //해당 정보를 통해 우리 서버에 이제 생성해야함
        log.info("서버 username {}", kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        log.info("서버 이메일 {}", kakaoProfile.getKakao_account().getEmail());
        UUID garbagePassword = UUID.randomUUID();
        log.info("서버에 랜덤으로 집어넣을 패스워드 {}", garbagePassword);

        return kakaoProfile;
    }

    public OauthToken getAccessToken(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + token); //(1-4)
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoApiKey);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        //4가지 모두 넣어줌.

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers); //body 데이터와 헤더값 가짐.

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        //ObjectMapper를 통해 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("카카오 엑세스 토큰 {}", oauthToken.getAccess_token());

        return oauthToken;
    }
}
