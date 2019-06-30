package mis.berritus.cloud.service.cust.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"mis.berritus.cloud.service.cust.dao"})
@ComponentScan({"mis.berritus.cloud.service.cust"})
@EnableFeignClients(basePackages = {"mis.berritus.cloud.service.cust.feign.client"})
@EnableHystrix//开启熔断器功能
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableHystrixDashboard//断路器仪表盘
@EnableEurekaClient
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
public class CloudServiceCustApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudServiceCustApplication.class, args);
    }
}
