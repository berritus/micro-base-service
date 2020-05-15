package mis.berritus.cloud.common.web.feign.client;

import mis.berritus.cloud.app.bean.uaa.MisJwt;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.common.web.feign.client.conf.DefaultFeignConfig;
import mis.berritus.cloud.common.web.feign.client.fallback.AuthServiceClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-uaa-jwt", fallback = AuthServiceClientFallBack.class, configuration = DefaultFeignConfig.class)
public interface AuthServiceClient {
    @PostMapping("/mis/noauth/matchesUser")
    SysUser matchesUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping("/mis/oauth/token")
    MisJwt getToken2(@RequestHeader(value = "Authorization") String authorization,
                     @RequestParam("grant_type") String type,
                     @RequestParam("username") String username,
                     @RequestParam("password") String password);

    @GetMapping("/mis/noauth/hello")
    String index(@RequestHeader(value = "str") String str);

    @PostMapping("/mis/noauth/add/clients")
    String oauthClientDetails(@RequestBody OauthClientDetails record);
}
