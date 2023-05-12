package inflearn.spring01.discount;

import inflearn.spring01.member.Grade;
import inflearn.spring01.member.Member;
import org.springframework.stereotype.Component;
@Component // 중복이 된다.
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
