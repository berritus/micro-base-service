package mis.berritus.cloud.uaa.controller;

import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.uaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noauth")
public class NoAuthController {

    @Autowired
    private SysService sysService;

    @PostMapping("/matchesUser")
    public SysUser matchesUser(String username, String password){
        return sysService.matchesUser(username, password);
    }

    @GetMapping("/")
    public String index(){
        return "This is auth service";
    }
}
