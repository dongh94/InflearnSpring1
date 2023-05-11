package inflearn.spring01.scan;

import inflearn.spring01.AutoAppConfig;
import inflearn.spring01.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    // ComponentScan 이 Component를 잡는다.
    // 그 의존관계는 (구체 클라쓰에서 파라미터를 받는 생성자) Autowired가 필요하다.
    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
