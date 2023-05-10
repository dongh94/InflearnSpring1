package inflearn.spring01.order;

import inflearn.spring01.discount.DiscountPolicy;
import inflearn.spring01.discount.FixDiscountPolicy;
import inflearn.spring01.discount.RateDiscountPolicy;
import inflearn.spring01.member.MemberRepository;
import inflearn.spring01.member.MemberService;
import inflearn.spring01.member.MemberServiceImpl;
import inflearn.spring01.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(MemberRepository());
    }
    @Bean
    public MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }

}
