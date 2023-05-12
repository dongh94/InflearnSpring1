package inflearn.spring01.autowired;

import inflearn.spring01.AutoAppConfig;
import inflearn.spring01.discount.DiscountPolicy;
import inflearn.spring01.member.Grade;
import inflearn.spring01.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllFunctionByMapAndListBeanTest {
    @Test
    @DisplayName("미리 만든 것을 Map, List로 담아서 테스트 해보기")
    void AllBeanToTDD() {
        // 0. 먼저 스프링 컨테이너 생성 = > 기능 만든 후 빈 등록( Type으로 )할 것. ( Autowired 까지 설정 )
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        // 3. 밑에 새로 만든 기능 스프링 빈 조회
        DiscountService bean = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        // Map
        int discountPrice = bean.discount(member, 10000, "fixDiscountPolicy");
        assertThat(bean).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = bean.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

        // List
        for (int i = 0 ; i < bean.policyList.size(); i++) {
            int discountPriceList = bean.discount(member, 20000, i);
            System.out.println("discountPriceList = " + discountPriceList);
        }

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        // 1. @Autowired 생성자 주입 생략 가능.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }
        // 2. 기능 구현
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        }

        public int discount(Member member, int price, int idx) {
            DiscountPolicy discountPolicy = policyList.get(idx);
            System.out.println("idx = " + idx);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);

        }
    }
}
