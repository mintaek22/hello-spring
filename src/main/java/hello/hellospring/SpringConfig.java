package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//자바 코드로 스프링 빈 등록
//나중에 코드 service 나 repository를 바꿀시 편하다
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    //Aop는 다른 것 들과 다르게 정형화된 툴을 아니여서 따로 bean을 등록 하는게 좋다(!=Component Scan)
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
  /*  @Bean
    public MemberRepository memberRepository(){
       // return new MemoryMemberRepository();
       // return new JdbcTemplateMemberRepository(dataSource);
       // return new JpaMemberRepository(em);
    }*/
}
