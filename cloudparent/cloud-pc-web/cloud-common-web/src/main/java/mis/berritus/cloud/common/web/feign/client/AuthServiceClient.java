package mis.berritus.cloud.common.web.feign.client;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.app.bean.uaa.MisJwt;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.common.web.feign.client.conf.DefaultFeignConfig;
import mis.berritus.cloud.common.web.feign.client.fallback.AuthServiceClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cloud-uaa-jwt", fallback = AuthServiceClientFallBack.class, configuration = DefaultFeignConfig.class)
public interface AuthServiceClient {
    @PostMapping("/mis/noauth/matchesUser")
    SysUserDTO matchesUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping("/mis/oauth/token")
    MisJwt getToken2(@RequestHeader(value = "Authorization") String authorization,
                     @RequestParam("grant_type") String type,
                     @RequestParam("username") String username,
                     @RequestParam("password") String password);

    @GetMapping("/mis/noauth/hello")
    String index(@RequestHeader(value = "str") String str);

    @PostMapping("/mis/noauth/add/clients")
    String addOauthClientDetails(@RequestBody OauthClientDetails record);

    @PostMapping("/mis/noauth/list/clients")
    PageInfo<OauthClientDetails> getOauthClientDetails(@RequestBody OauthClientDetails record);

    // http://localhost:5001/mis/noauth/del/clients
    @PostMapping("/mis/noauth/del/clients")
    boolean delOauthClientDetails(@RequestParam("clientId") String clientId);

    @PostMapping("/mis/sys/user/list")
    PageInfo<SysUserDTO> listSysUser(@RequestBody SysUserDTO record);

    @PostMapping("/mis/sys/user/add")
    SysUserDTO insertSysUser(@RequestBody SysUserDTO record);

    @GetMapping("/mis/sys/user/get")
    SysUserDTO getSysUser(@RequestParam("userName") String userName);

    @PostMapping("/mis/sys/user/del")
    void delSysUser(@RequestParam("seqId") Long seqId);
}
