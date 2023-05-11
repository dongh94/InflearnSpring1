package inflearn.spring01.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("상태를 공유하는 것을 변동하면 안되는 이유 => 싱글톤 문제점")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA = A 사용자 10000원 주문
        statefulService1.order("A", 10000);
        // ThreadB = B 사용자 20000원 주문
        statefulService1.order("B", 20000);
        // ThreadA = A 사용자 가격 조회
        int price = statefulService1.getPrice();
        // B가격이 A 사용자에게 조회된다는 크크으으은 문제가 발생.
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isNotEqualTo(10000);

        // 해결책
//        int userAprice = statefulService1.order("A", 10000);
//        int userBprice = statefulService1.order("B", 20000);
//        System.out.println("userAprice = " + userAprice + "userBprice = " + userBprice);


    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}