package inflearn.spring01.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    private String url;
    // 객체 생성
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }
    // 객체 초기화
    public void setUrl(String url) {
        this.url = url;
    }
    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect url : " + url);
    }
    //
    public void call(String msg) {
        System.out.println("call url : " + url + "msg = " + msg);

    }
    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close url = " + url);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
