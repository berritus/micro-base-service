package mis.berritus.cloud.config.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@RefreshScope
@EnableEurekaClient
public class CloudConfigApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudConfigApplication.class, args);
    }
}
