package mis.berritus.cloud.mongodb.conf;

import com.berritus.mis.core.cache.annotation.EnableMisCache;
import com.berritus.mis.core.controller.annotation.EnableMisController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/31
 */
@EnableEurekaClient
@ComponentScan({"mis.berritus.cloud.mongodb"})
@SpringBootApplication
// @EnableFeignClients(basePackages = {"mis.berritus.cloud.elasticsearch.feign.client"})
@EnableHystrix//开启熔断器功能
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableHystrixDashboard//断路器仪表盘
@EnableMisCache
@EnableMisController
public class MongodbApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }
}
