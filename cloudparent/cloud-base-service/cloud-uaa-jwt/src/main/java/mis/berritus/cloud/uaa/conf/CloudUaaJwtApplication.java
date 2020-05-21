package mis.berritus.cloud.uaa.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableWebSecurity
@EnableEurekaClient
@SpringBootApplication
@ComponentScan({"mis.berritus.cloud.uaa"})
@MapperScan({"mis.berritus.cloud.uaa.dao"})
@EntityScan({"mis.berritus.cloud.bean"})
@EnableAuthorizationServer
//@EnableRedisHttpSession
public class CloudUaaJwtApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudUaaJwtApplication.class, args);
    }
}
