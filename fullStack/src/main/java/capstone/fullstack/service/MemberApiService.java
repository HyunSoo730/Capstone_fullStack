//package capstone.fullstack.service;
//
//import capstone.fullstack.dto.KakaoProfile;
//import capstone.fullstack.dto.OauthToken;
//import capstone.fullstack.repository.MemberRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//@Slf4j
//public class MemberApiService {
//
//    private final MemberRepository memberRepository;
//
//    /**
//     * access token으로 해당 사용자의 정보 받아와서 DB에 저장.
//     */
//    public void save(String token) {
//        //(1)
//        KakaoProfile profile = findProfile(token);
//
//        //(2)
//        User user = memberRepository.findByEmail(profile.getKakao_account().getEmail());
//
//        //(3)
//        if(user == null) {
//            user = User.builder()
//                    .kakaoId(profile.getId())
//                    //(4)
//                    .kakaoProfileImg(profile.getKakao_account().getProfile().getProfile_image_url())
//                    .kakaoNickname(profile.getKakao_account().getProfile().getNickname())
//                    .kakaoEmail(profile.getKakao_account().getEmail())
//                    //(5)
//                    .userRole("ROLE_USER").build();
//
//            userRepository.save(user);
//        }
//
//        return user;
//    }
//
//
//    /**
//     * access token으로 카카오 서버에서 사용자 정보를 가져온다.
//     */
//    //(1-1)
//    public KakaoProfile findProfile(String token) {
//
//        //(1-2)
//        RestTemplate rt = new RestTemplate();  //통신에 필요한 RestTemplate 객체를 만든다.
//
//        //(1-3)
//        HttpHeaders headers = new HttpHeaders();  //HttpHeader 헤더에는 발급 받은 엑세스 토큰을 넣어 요청해야 한다.
//        headers.add("Authorization", "Bearer " + token); //(1-4)
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        //(1-5)
//        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
//                new HttpEntity<>(headers);
//
//        //(1-6)
//        // Http 요청 (POST 방식) 후, response 변수에 응답을 받음
//        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoProfileRequest,
//                String.class
//        );
//
//        //(1-7)
//        ObjectMapper objectMapper = new ObjectMapper();
//        KakaoProfile kakaoProfile = null;
//        try {
//            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return kakaoProfile;
//    }
//
//}
