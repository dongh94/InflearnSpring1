package inflearn.spring01.autowired;

import inflearn.spring01.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        // @Test : 스프링 컨테이너가 만들어 지면서 TestConfig 설정이 스프링 빈에 등록이 된다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        // 이후 스프링 빈에 등록될테니 내부 메서드에서 콘솔 확인해보자.
    }

    static class TestConfig {
        // autowired든 spring container는 Type으로 조회하기 때문에 .class 로 하는 것.
        // Member는 스프링 빈에 등록되지 않은 상태입니다. NoBean : 스프링 빈 등록과 주입을 구분함.
        // 내부 스프링 빈 @ComponentScan된 것에서 찾게 되는데 => 스프링 컨테이너에 스프링 빈 등록
        // @Autowired를 하면 자동 의존성 주입을 시작하는데 = > AppConfig에 따로 주입 안해도 된다는 의미.
        // 당연히 @Autowired가 없으면 그냥 둔다. = > 당연히 스프링 컨테이너에서 꺼내서 쓰는데. 없잖아.
        // 그럼, Autowired된 메서드 내부 객체들은 모두 스프링 빈에 있어야 함.
        // 근데 없는데 되는 게 있다? @Nullable , Optional<> !! = > 자동으로 스프링 컨테이너에 들어간다는 의미.

        //Member 때문에 호출 안됨. => required = false로 두면 메서드 자체 호출 안하는 것.
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("member = " + member);
        }

        // 호출 됩니다. @Nullable => null
        @Autowired
        public void setNoBean2(@Nullable Member member2) {
            System.out.println("member2 = " + member2);
        }

        // 호출 됩니다. Optional<> => Optional.empty
        @Autowired
        public void setNoBean3(Optional<Member> member3) {
            System.out.println("member3 = " + member3);
        }
    }
}
