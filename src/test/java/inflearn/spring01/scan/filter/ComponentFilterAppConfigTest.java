package inflearn.spring01.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterscan() {
        // 직접 만든 ComponentFilterAppConfig 넣으세요. => include, exclude 직접 만들어서 TEST해봅니다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        MyBeanA myBeanA = ac.getBean("myBeanA", MyBeanA.class);
//        MyBeanB myBeanB = ac.getBean("myBeanB", MyBeanB.class); // Exclude라서 그냥 돌리면 error 뜹니다.
        assertThat(myBeanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("myBeanB", MyBeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
