package mis.berritus.cloud.service.prod.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan({"mis.berritus.cloud.service.prod"})
@MapperScan({"mis.berritus.cloud.service.prod.dao"})
@EnableFeignClients(basePackages = {"mis.berritus.cloud.service.prod.feign.client"})
@EnableResourceServer
@EnableHystrix//开启熔断器功能
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableHystrixDashboard//断路器仪表盘
public class ProductServerJwtApplication {
    public static void main(String[] args){
        SpringApplication.run(ProductServerJwtApplication.class, args);
    }
}
