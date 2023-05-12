package inflearn.spring01.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    // 중복이 될 때, Qulifier의 오류를 줄이기 위해 (String은 Debug가 쉽지 않으므로)
    // 직접 Annotation을 만들어서 사용하곤 한다.
    // 사용법 : RateDiscountPolicy를 사용하고 싶을 때, 해당 구체에 위의 Annotation을 달아주고, 사용하는 곳에도 달아준다.
    // 왜?
    // 1. Annotation은 상속이 없다.
    // 2. 다른 Qulifier과 조합이 가능하다.
    // 3. 다만, 무분별한 재정의는 삼가하자.
}
