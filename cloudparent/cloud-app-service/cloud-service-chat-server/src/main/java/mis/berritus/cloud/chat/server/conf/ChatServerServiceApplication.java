package mis.berritus.cloud.chat.server.conf;

import com.berritus.mis.core.controller.annotation.EnableMisController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/4/27
 */
@EnableEurekaClient
@ComponentScan({"mis.berritus.cloud.chat"})
@SpringBootApplication()
// @EnableFeignClients(basePackages = {"mis.berritus.cloud.elasticsearch.feign.client"})
@EnableHystrix//开启熔断器功能
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableHystrixDashboard//断路器仪表盘
@EnableMisController
public class ChatServerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatServerServiceApplication.class, args);
    }
}
