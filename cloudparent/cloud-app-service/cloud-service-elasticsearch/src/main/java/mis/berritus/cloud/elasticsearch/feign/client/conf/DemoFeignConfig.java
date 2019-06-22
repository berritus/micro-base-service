package mis.berritus.cloud.elasticsearch.feign.client.conf;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class DemoFeignConfig {
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100, SECONDS.toMillis(10000), 5);
    }

    @Bean
    public Request.Options options(){
        return new Request.Options(60000, 60000);
    }
}
