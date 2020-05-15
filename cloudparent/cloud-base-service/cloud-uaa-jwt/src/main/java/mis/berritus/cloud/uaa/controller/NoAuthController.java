package mis.berritus.cloud.uaa.controller;

import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.uaa.service.AuthService;
import mis.berritus.cloud.uaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noauth")
public class NoAuthController {

    @Autowired
    private SysService sysService;
    @Autowired
    private AuthService authService;

    @PostMapping("/matchesUser")
    public SysUser matchesUser(String username, String password){
        return sysService.matchesUser(username, password);
    }

    // http://localhost:5001/mis/noauth/hello
    @GetMapping("/hello")
    public String index(String str){
        return "This is auth service " + str;
    }

    // 添加接入的服务 oauth_client_details表， 也就是在登陆验证的时候 authServiceClient.getToken2第一个参数加密的
    @PostMapping("/add/clients")
    public String oauthClientDetails(@RequestBody OauthClientDetails record){
        authService.insertOauthClientDetails(record);
        return "0";
    }
}
