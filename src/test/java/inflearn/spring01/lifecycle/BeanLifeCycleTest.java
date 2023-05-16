package inflearn.spring01.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    //
    @Test
    void lifeCycleTest() {
        // 스프링 컨테이너 생성, 빈 생성, 의존관계 주입 => 초기화 콜백 => 로직 => 소멸자 콜백 => 스프링 종료
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient bean = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        // 생성자에 초기 정보를 기입하면 추후 초기화콜백을 따로 실행하지 않기 때문에 이를 지정해줘야 한다.
        // 생성자 호출, url = null
        // 1. 인터페이스 => implements InitializingBean , DisposableBean ((생성=> 초기), 소멸 콜백)=> Overide
        // 2. 빈 등록 ((생성=>초기), 소멸) => 빈이 코드에 의존하지 않고 메서드 이름 자유도가 높다.
        // 3. 애노테이션 !!! <권장>  @PostConstruct     @PreDestroy

//        @Bean(initMethod = "init")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hellodonghee.dev");
            return networkClient;
        }
    }
}
