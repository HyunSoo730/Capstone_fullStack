package capstone.fullstack.service;

import capstone.fullstack.domain.Member;
import capstone.fullstack.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setEmail("hyunsoo730@naver.com");
        //when
        Member saveMember = memberService.join(member);
        //then
        Assertions.assertThat(saveMember).isEqualTo(memberRepository.findByEmail(member.getEmail()));

    }


    @Test
    void 중복_회원_검증(){
        //given
        Member member = new Member();
        member.setEmail("hyunsoo730@naver.com");

        Member member2 = new Member();
        member2.setEmail("hyunsoo730@naver.com");
        //when
        memberService.join(member);
        memberService.join(member2);  //예외가 발생해야 한다.
        //then
        fail("예외가 발생해야 한다.");
    }


    @Test
    void 로그인_검증(){
        Member member = new Member();
        member.setEmail("hyunsoo730@naver.com");
        member.setPassword("asd");
        memberService.join(member);
        memberService.findMember(member);

    }

}