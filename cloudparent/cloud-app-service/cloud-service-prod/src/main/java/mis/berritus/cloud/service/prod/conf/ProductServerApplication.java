package mis.berritus.cloud.service.prod.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan({"mis.berritus.cloud.service.prod"})
@MapperScan({"mis.berritus.cloud.service.prod.dao"})
@EnableResourceServer
public class ProductServerApplication {
    public static void main(String[] args){
        SpringApplication.run(ProductServerApplication.class, args);
    }
}
