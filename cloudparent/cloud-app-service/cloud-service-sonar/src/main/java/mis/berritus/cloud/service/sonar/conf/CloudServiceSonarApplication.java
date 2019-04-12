package mis.berritus.cloud.service.sonar.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"mis.berritus.cloud.service.sonar", "mis.berritus.cloud.app.common"})
@EnableEurekaClient
@SpringBootApplication
public class CloudServiceSonarApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudServiceSonarApplication.class);
    }
}
