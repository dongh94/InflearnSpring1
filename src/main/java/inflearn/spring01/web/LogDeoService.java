package inflearn.spring01.web;

import inflearn.spring01.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDeoService {
//    private final MyLogger myLogger; // Scope 문제로 인해 스프링 빈 등록이전에 사용할 수 없는 문제 발생.
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    public void logic(String id) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
