package inflearn.spring01.web;

import inflearn.spring01.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    // 'myLogger': Scope 'request' is not active for the ..
//    처음에 해당 에러가 뜨는 이유는 request scope 의 myLogger 의 생존주기와 관련이 깊다.
    // 해결 1. Provider !
    private final LogDeoService logDeoService;
//    private final MyLogger myLogger;  // 위의 에러로 인해 그냥 쓰면 요청받기 전까지 사용할 수 없다.
    private final ObjectProvider<MyLogger> myLoggerObjectProvider; // 호출하기 전까지 지연할 수 있다.
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();

        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller hello");
        logDeoService.logic("test ID");
        return "OK";
    }
}
