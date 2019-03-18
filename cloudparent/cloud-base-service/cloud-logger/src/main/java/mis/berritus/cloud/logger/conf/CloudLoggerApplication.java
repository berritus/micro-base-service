package mis.berritus.cloud.logger.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"mis.berritus.cloud.logger.dao"})
@ComponentScan({"mis.berritus.cloud.logger"})
@EnableEurekaClient
@EnableTransactionManagement
public class CloudLoggerApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudLoggerApplication.class);
    }
}
