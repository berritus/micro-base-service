package mis.berritus.cloud.common.web.feign.client.conf;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/13
 */
@Configuration
public class DefaultFeignConfig {
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100, SECONDS.toMillis(10000), 5);
    }

    @Bean
    public Request.Options options(){
        return new Request.Options(60000, 60000);
    }
}
