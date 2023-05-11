package inflearn.spring01;

import inflearn.spring01.discount.DiscountPolicy;
import inflearn.spring01.discount.FixDiscountPolicy;
import inflearn.spring01.discount.RateDiscountPolicy;
import inflearn.spring01.member.MemberRepository;
import inflearn.spring01.member.MemberService;
import inflearn.spring01.member.MemberServiceImpl;
import inflearn.spring01.member.MemoryMemberRepository;
import inflearn.spring01.order.OrderService;
import inflearn.spring01.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService => new MemoryMemberRepository()
    // @bean orderService => MemoryMemberRepository()

    // Bean이 싱글톤으로 재사용 관리를 안해줬다면 ?
    // call AppConfig.memberService
    // call AppConfig.memberRepository

    // call AppConfig.memberRepository

    // call AppConfig.orderService
    // call AppConfig.memberRepository
    // call AppConfig.discountPolicy

    // call AppConfig.discountPolicy
    // 실제로는 ??
    // 각 1번씩만 부른다. 이것이 Configration의 DI의 힘.


    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }

}
