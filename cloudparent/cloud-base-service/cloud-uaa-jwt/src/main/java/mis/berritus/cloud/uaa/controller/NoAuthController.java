package mis.berritus.cloud.uaa.controller;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.uaa.service.AuthService;
import mis.berritus.cloud.uaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noauth")
public class NoAuthController {

    @Autowired
    private SysService sysService;
    @Autowired
    private AuthService authService;

    @PostMapping("/matchesUser")
    public SysUserDTO matchesUser(String username, String password){
        return sysService.matchesUser(username, password);
    }

    // http://localhost:5001/mis/noauth/hello
    @GetMapping("/hello")
    public String index(String str){
        return "This is auth service " + str;
    }

    // http://localhost:5001/mis/noauth/add/clients
    // 添加接入的服务 oauth_client_details表， 也就是在登陆验证的时候 authServiceClient.getToken2第一个参数加密的
    @PostMapping("/add/clients")
    public String addOauthClientDetails(@RequestBody OauthClientDetails record){
        authService.insertOauthClientDetails(record);
        return "0";
    }

    //layui 不支持restful风格
    // http://localhost:5001/mis/noauth/list/clients
    @PostMapping("/list/clients")
    public PageInfo<OauthClientDetails> getOauthClientDetails(@RequestBody OauthClientDetails record){
        return authService.listOauthClientDetails(record);
    }

    // http://localhost:5001/mis/noauth/del/clients
    @PostMapping("/del/clients")
    public boolean delOauthClientDetails(String clientId){
        return authService.delOauthClientDetails(clientId);
    }
}
