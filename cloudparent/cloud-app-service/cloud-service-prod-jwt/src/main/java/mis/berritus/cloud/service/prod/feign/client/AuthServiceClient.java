package mis.berritus.cloud.service.prod.feign.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import mis.berritus.cloud.app.bean.uaa.MisJwt;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.service.prod.feign.client.conf.DemoFeignConfig;
import mis.berritus.cloud.service.prod.feign.client.fallback.AuthServiceClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-uaa", fallback = AuthServiceClientFallBack.class, configuration = DemoFeignConfig.class)
public interface AuthServiceClient {
    @PostMapping("/mis/noauth/matchesUser")
    SysUser matchesUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping("/mis/oauth/token")
    MisJwt getToken2(@RequestHeader(value="Authorization")String authorization,
                     @RequestParam("grant_type") String type,
                     @RequestParam("username") String username,
                     @RequestParam("password") String password);
}
