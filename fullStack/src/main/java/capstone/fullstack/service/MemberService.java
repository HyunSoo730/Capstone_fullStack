//package capstone.fullstack.service;
//
//import capstone.fullstack.domain.Member;
//import capstone.fullstack.dto.MemberDto;
//import capstone.fullstack.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional  //값이 변하는 과정에는 모두 Transactional 필요
//@RequiredArgsConstructor
//@Slf4j
//public class MemberService {
//
//    @Autowired
//    private final MemberRepository memberRepository;
//
//    /**
//     * 회원 가입
//     * @param member
//     * @return
//     */
//    public Member join(Member member) {
//        validateEmail(member);    //이메일 중복 확인
//        //중복 회원이 아니라면
//        log.info("중복 회원 아님");
//        memberRepository.save(member);
//        return member;  //회원가입한 회원 반환.
//    }
//    /**
//     * 중복 이메일 검증
//     * @param member
//     */
//    private void validateEmail(Member member) {
//        List<Member> findMember = memberRepository.findByEmail(member.getEmail());
//        if (findMember.isEmpty() == false) {  //비어있지 않으면 존재하니깐.
//            throw new RuntimeException("recheck");
//        }
//    }
//
//    /**
//     * 로그인 + 로그인 정보가 올바른지 확인
//     * @param member
//     * @return
//     */
//    public Member findMember(Member member) {
//        validateMember(member);   //회원 정보 맞는지 검증.
//        //로그인이 완료 되었다면
//        log.info("로그인 검증 성공");
//        return member;  //회원 정보 반환
//    }
//
//    /**
//     * 로그인 검증
//     * @param member
//     */
//    private void validateMember(Member member) {
//        Member res = memberRepository.findByEmailAndPassword(member.getEmail(), member.getPassword());
//        log.info("Email = {} password = {}", res.getEmail(), res.getPassword());
//        if (res == null) {  //해당 이메일과 비번으로 찾을 수 없다면. 에러 처리해야지.
//            throw new RuntimeException("recheck");
//        }
//    }
//}
