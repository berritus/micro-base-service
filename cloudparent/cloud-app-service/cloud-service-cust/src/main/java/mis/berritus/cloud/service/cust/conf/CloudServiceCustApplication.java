package mis.berritus.cloud.service.cust.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"mis.berritus.cloud.service.cust.dao"})
@ComponentScan({"mis.berritus.cloud.service.cust"})
@EnableEurekaClient
@EnableTransactionManagement
@SpringBootApplication
public class CloudServiceCustApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudServiceCustApplication.class);
    }
}
