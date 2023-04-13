//package capstone.fullstack.controller;
//
//import capstone.fullstack.service.MemberApiService;
//import capstone.fullstack.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class MemberApiController {
//
//    private final MemberApiService memberApiService;
//
//    /**
//     * access token 프론트에서 받아오면 카카오 회원 정보 DB저장
//     */
//    @PostMapping("/member/login")
//    public String login(@RequestBody String accessToken) {
//        memberApiService.save(accessToken);
//    }
//}
