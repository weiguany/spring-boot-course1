package top.wgy.boot.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.wgy.boot.filter_interceptor.filter.RateLimitFilter;

@Configuration
@AllArgsConstructor
public class FilterConfig {
//    private final MyFilter myFilter;
//    private final YourFilter yourFilter;

//    @Bean
//    public FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean(){
//        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(myFilter);
//        registrationBean.addUrlPatterns("/api/test");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<YourFilter> yourFilterFilterRegistrationBean(){
//        FilterRegistrationBean<YourFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(yourFilter);
//        registrationBean.addUrlPatterns("/api/test");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterRegistrationBean(RateLimitFilter rateLimitFilter){
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rateLimitFilter);
        registrationBean.addUrlPatterns("/api/pay/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}