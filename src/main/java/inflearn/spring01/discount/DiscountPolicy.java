package inflearn.spring01.discount;

import inflearn.spring01.member.Member;
import org.springframework.stereotype.Component;

@Component
public interface DiscountPolicy {
    /**
     * @return 할인 대상금액
     */
    int discount(Member member, int price);

}
