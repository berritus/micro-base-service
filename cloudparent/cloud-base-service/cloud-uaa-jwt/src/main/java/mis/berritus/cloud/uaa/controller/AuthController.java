package mis.berritus.cloud.uaa.controller;

import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.uaa.service.AuthService;
import mis.berritus.cloud.uaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private SysService sysService;

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public Principal getUser(Principal principal){
        return principal;
    }

    // 添加接入的服务 oauth_client_details表， 也就是在登陆验证的时候 authServiceClient.getToken2第一个参数加密的
    @PostMapping("/add/client")
    @PreAuthorize("hasAuthority('ROLE_OAUTH_CLIENT_ADMIN')")
    public String insertOauthClientDetails(@RequestBody OauthClientDetails record){
        authService.insertOauthClientDetails(record);
        return "0";
    }

}
