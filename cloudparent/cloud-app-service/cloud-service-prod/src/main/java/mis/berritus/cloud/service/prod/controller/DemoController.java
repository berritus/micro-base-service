package mis.berritus.cloud.service.prod.controller;

import mis.berritus.cloud.app.bean.prod.SysProduct;
import mis.berritus.cloud.service.prod.service.DemoService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(method = RequestMethod.GET, value = "/prod/add")
    public SysProduct addTbSysProduct(){
        SysProduct prod = new SysProduct();
        prod.setProName("prod11");
        prod.setCode("PORD_V22");
        return demoService.addTbSysProduct(prod);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hi")
    public String hihi(){
        return "hi";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/hi/hello")
    public String hello(){
        return "hello world";
    }

    @PreAuthorize("hasAuthority('ROLE_MIS')")
    @RequestMapping(method = RequestMethod.GET, value = "/hi/mis")
    public String helloMis(){
        return "success mis";
    }

    //用于获取当前用户token
    @RequestMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal,
                                             Authorization authorization){
        String str = oAuth2Authentication.toString() + "," + principal.toString() + ","
                + principal.getName() + "," + authorization.toString();
        System.out.println(str);
        return oAuth2Authentication;
    }
}
