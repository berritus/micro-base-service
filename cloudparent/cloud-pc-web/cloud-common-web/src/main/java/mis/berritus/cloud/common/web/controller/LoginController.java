package mis.berritus.cloud.common.web.controller;

import mis.berritus.cloud.app.bean.uaa.MisJwt;
import mis.berritus.cloud.app.common.utils.DESUtils;
import mis.berritus.cloud.bean.sys.service.LoginInfo;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.common.web.feign.client.AuthServiceClient;
import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/15
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthServiceClient authServiceClient;

    // http://localhost:8110
    @RequestMapping("/")
    public String index(Model model) {
        return "study/main/login";
    }

    // http://localhost:8110/index
    @RequestMapping("/index")
    public String indexPage(Model model, HttpServletRequest request) {
        // model.addAttribute("message", "你好啊");
        String accessToken = request.getParameter("access_token");
        model.addAttribute("accessToken", accessToken);
        return "study/main/index";
    }

    //用于获取当前用户token
    @RequestMapping("/getPrinciple")
    @ResponseBody
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal,
                                             Authorization authorization){
        String str = oAuth2Authentication.toString() + "," + principal.toString() + ","
                + principal.getName() + "," + authorization.toString();
        System.out.println(str);
        return oAuth2Authentication;
    }

    //@RequestMapping(method = RequestMethod.POST, value = "/user/login")
    @PostMapping("/user/login")
    @ResponseBody
    public ModelAndView login(@RequestBody LoginInfo loginInfo){
        Map<String, Object> map = new HashMap<>();
        // ModelAndView modelAndView = new ModelAndView();
        try {
            //SysUserExt sysUserExt = new SysUserExt();
            String username = loginInfo.getUsername();
            String password = loginInfo.getPassword();
            SysUser sysUser = authServiceClient.matchesUser(username, password);

            if(sysUser != null){
                //dXNlci1zZXJ2aWNlOjEyMzQ1Ng== 是 user-service:123456的 base64编码
                String authStr = DESUtils.tranToBase64("cloud-common-web:q123456");
                MisJwt jwt = authServiceClient.getToken2("Basic " + authStr,
                        "password", username, password);
                if(jwt == null){
                    throw new RuntimeException("用户token错误");
                }

                // sysUserExt.setSysUser(sysUser);
                // sysUserExt.setMisJwt(jwt);

                map.put("code", 0);
                map.put("msg", "登陆成功");
                map.put("access_token", jwt.getAccess_token());
                map.put("refresh_token", jwt.getRefresh_token());
//                modelAndView.addObject("sysUser", sysUserExt);
//                modelAndView.addObject("msg", "登陆成功");
//                modelAndView.setViewName("study/main/index");

//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//                MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
//                MultiValueMap<String, String> resourceparams= new LinkedMultiValueMap<>();
//                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(resourceparams, headers);
//                ResponseEntity<String> response =  response = restTemplate.postForEntity("http://localhost:8081/product/1", requestEntity, String.class);
//                resourceparams.add("access_token",acccessToken);
//                request.getHeaders();
            }
        } catch (Exception e) {
            logger.error("登陆失败，{}",e);
            map.put("code", -1);
            map.put("msg", "登陆失败");
        }

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
