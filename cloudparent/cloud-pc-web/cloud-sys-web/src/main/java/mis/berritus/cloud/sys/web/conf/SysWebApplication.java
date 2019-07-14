package mis.berritus.cloud.sys.web.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/14
 */
@EnableEurekaClient
@ComponentScan({"mis.berritus.cloud.sys.web"})
@SpringBootApplication
@EnableFeignClients(basePackages = {"mis.berritus.cloud.sys.web.feign.client"})
@EnableHystrix//开启熔断器功能
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableHystrixDashboard//断路器仪表盘
@EnableResourceServer
public class SysWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysWebApplication.class, args);
    }
}
