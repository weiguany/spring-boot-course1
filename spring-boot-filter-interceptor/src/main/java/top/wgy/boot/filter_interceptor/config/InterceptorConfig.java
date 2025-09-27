package top.wgy.boot.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author your-name
 * @date 2024/1/1
 */
@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
//    private final Myinterceptor myInterceptor;
//    private final Yourinterceptor yourInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor).addPathPatterns("/api/test");
//        registry.addInterceptor(yourInterceptor).addPathPatterns("/api/test");
//
//    }
}
