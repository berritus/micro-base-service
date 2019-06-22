package mis.berritus.cloud.sys.service;

import com.berritus.mis.core.cache.annotation.EnableMisCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@MapperScan({"mis.berritus.cloud.sys.service.dao"})
@ComponentScan({"mis.berritus.cloud"})
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
//@EnableScheduling
@EnableMisCache
//@EnableFeignClients(basePackages = {"com.berritus.cloud.message.feign.client"})
public class SysServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysServiceApplication.class);
    }
}
