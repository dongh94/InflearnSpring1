package inflearn.spring01.discount;

import inflearn.spring01.member.Grade;
import inflearn.spring01.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // 중복이 된다.
@Primary // 우선순위로 결정이 된다. 해결
//@Qualifier("mainDiscountPolicy") => 사용할 때 parameter에도 @Qualifier("mainDiscountPolicy") 입력해준다. 우선순위가 높다.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
