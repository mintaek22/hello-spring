package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        //ctrl + alt + v 변수 이름 지정
        Long saveId = memberService.join(member);


        //then
        //shift + enter -> static import 만들기
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    //ctrl + w 포커스 영역 확장장
    //ctrl + shift + / 해당 블록 주석 처리
   @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        /* memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        }*/
       memberService.join(member1);
       IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

       assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

   }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}