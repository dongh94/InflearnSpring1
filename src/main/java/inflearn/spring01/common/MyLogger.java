package inflearn.spring01.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.UUID;

//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)) // proxy로 provider를 대체할 수 있다. 다형성과 DI 컨테이너의 핵심
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + " message : " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this );

    }

    @PreDestroy
    public void destory() {
        System.out.println("[" + uuid + "] request scope bean close : " + this );

    }
}
