package mis.berritus.cloud.uaa.controller;

import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.uaa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public Principal getUser(Principal principal){
        return principal;
    }

    @GetMapping("/")
    public String index(){
        return "This is auth service";
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ROLE_OAUTH_CLIENT_ADMIN')")
    public String insertOauthClientDetails(@RequestBody OauthClientDetails record){
        authService.insertOauthClientDetails(record);
        return "0";
    }
}
