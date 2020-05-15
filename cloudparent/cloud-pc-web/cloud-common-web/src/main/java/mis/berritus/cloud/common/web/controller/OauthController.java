package mis.berritus.cloud.common.web.controller;

import mis.berritus.cloud.app.bean.common.ResultVO;
import mis.berritus.cloud.app.common.utils.CommonUtil;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.common.web.feign.client.AuthServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/15
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {
    private static final Logger logger = LoggerFactory.getLogger(OauthController.class);

    @Autowired
    private AuthServiceClient authServiceClient;

    // http://localhost:8113/oauth/cliet/details
    @PreAuthorize("hasAuthority('ROLE_OAUTH_CLIENT_ADMIN')")
    @RequestMapping("/cliet/details")
    public ModelAndView oauthClientDetails(@RequestBody OauthClientDetails record) {
        Map<String, Object> map = new HashMap<>();
        ResultVO resultVO = null;
        try {
            authServiceClient.oauthClientDetails(record);
            resultVO = CommonUtil.getResultVO(true, 0, "成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
