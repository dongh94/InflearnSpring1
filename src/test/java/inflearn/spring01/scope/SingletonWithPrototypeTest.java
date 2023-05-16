package inflearn.spring01.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest {
    // DL => Provider를 사용하라
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }
    // prototype을 singleton 안에서 사용하는 예시 (이걸 원한게 아니겠지만)
    @Scope("singleton")
    @RequiredArgsConstructor // 생성자 하나 주입 final 필수
    static class ClientBean {
//        private final PrototypeBean prototypeBean; // 생성 시점에 주입 => 사용할때마다 생성 및 주입하고 싶음.

//        @Autowired => 생성자 하나 주입 final 모두 => Annotate로 해결
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//        @Autowired
//        ApplicationContext applicationContext; // => 사용할때마다 생성 및 주입하고 싶어서 하드코딩

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider; // 별도 gradle 없이 DL 기능
//        => ObjectProvider => DL => Lookup(조회) 해서 호출시점까지 생성을 지연해주는 Provider
//        => 서버 처음 돌릴 때 문제해결(Http 호출 시점 등)
//        => 각각의 프로토타입을 보장(User 내용 보장)
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider; // JSR303 Provider gradle injection 필수 => 라이브러리관계 X 사용

        public int logic() {  // => 사용할때마다 생성 및 주입하고 싶어서 하드코딩
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject(); // DL로 해결
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // JSR303 Provider gradle injection 필수
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }


    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}
