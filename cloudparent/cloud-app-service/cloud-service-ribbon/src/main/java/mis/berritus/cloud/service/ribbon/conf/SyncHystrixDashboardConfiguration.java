package mis.berritus.cloud.service.ribbon.conf;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCircuitBreaker
public class SyncHystrixDashboardConfiguration {

//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignHystrixBuild(){
//        return Feign.builder();
//    }

    //http://localhost:8092/hystrix
    //http://localhost:8092/actuator/hystrix.stream
    // SpringBoot2.0以后，不提供 hystrix.stream节点，需要自己增加
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
