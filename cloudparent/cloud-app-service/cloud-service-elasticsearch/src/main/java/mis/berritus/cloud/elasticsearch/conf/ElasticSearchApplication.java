package mis.berritus.cloud.elasticsearch.conf;

import com.berritus.mis.core.cache.annotation.EnableMisCache;
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
 * @Date: Create in 2019/5/16
 */
@EnableEurekaClient
@ComponentScan({"mis.berritus.cloud.elasticsearch"})
@SpringBootApplication
@EnableFeignClients(basePackages = {"mis.berritus.cloud.elasticsearch.feign.client"})
@EnableHystrix//开启熔断器功能
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableHystrixDashboard//断路器仪表盘
@EnableMisCache
public class ElasticSearchApplication {
    public static void main(String[] args) {
        /**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
