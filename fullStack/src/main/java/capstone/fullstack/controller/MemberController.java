//package capstone.fullstack.controller;
//
//import capstone.fullstack.domain.Member;
//import capstone.fullstack.dto.MemberDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 회원가입 api, 로그인 api를 연결시키는 컨트롤러
// * controller -> service
// */
//@RestController
//@RequiredArgsConstructor
//public class MemberController {
//
//    @Autowired
//    private final MemberService memberService;
//
//    /**
//     * 회원 가입 api -> email 중복 확인
//     * 회원가입 완료 후 회원 정보 반환
//     *
//     * @param memberDto
//     * @return 회원가입 완료한 member
//     */
//    @PostMapping("/signup")
//    public Member joinMember(@RequestBody MemberDto memberDto) {
//        Member member = new Member();
//        member.setEmail(memberDto.getEmail());
//        member.setName(memberDto.getName());
//        member.setPassword(memberDto.getPassword());
//        memberService.join(member);
//        return member;  // 회원 반환
//    }
//
//    /**
//     * 로그인 api -> 정보 조회해서 일치하는지 확인
//     * 이메일과 비밀번호 일치하는지 확인 후 회원 정보 반환.
//     */
//    @PostMapping("/login")
//    public Member loginMember(@RequestBody MemberDto memberDto) {
//        Member member = new Member();
//        member.setEmail(memberDto.getEmail());
////        member.setName(memberDto.getName());  이메일이랑 패스워드만 받아와서 검증
//        member.setPassword(memberDto.getPassword());
//        memberService.findMember(member);  //로그인 정보 올바르지 않았다면 여기서 오류 반환
//        return member;
//    }
//}
