package mis.berritus.cloud.common.web.controller;

import com.berritus.mis.core.bean.page.MisPage;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.app.bean.common.ResultVO;
import mis.berritus.cloud.app.common.utils.CommonUtil;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.common.web.feign.client.AuthServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
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
    @RequestMapping(method = RequestMethod.POST, value = "/cliet/details")
    public ModelAndView addOauthClientDetails(@RequestBody OauthClientDetails record) {
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        try {
            authServiceClient.addOauthClientDetails(record);
            resultVO = CommonUtil.getResultVO(true, 0, "成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    // 注意后台传递的数据格式，{'code':0,'msg':'','count':'数据总数','data':[]} 一定要是这样的模式
    @PreAuthorize("hasAuthority('ROLE_OAUTH_CLIENT_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/cliet/details/list")
    public ModelAndView getOauthClientDetails(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>(10);
        try {
            OauthClientDetails bean = new OauthClientDetails();
            bean.setPageNum(page);
            bean.setPageSize(limit);
            PageInfo<OauthClientDetails> plist = authServiceClient.getOauthClientDetails(bean);

            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", plist.getTotal());
            map.put("data", plist.getList());
        } catch (Exception e) {
            logger.error("查询失败，{}",e);
            map.put("code", -1);
            map.put("msg", "查询失败");
        }

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


    @PreAuthorize("hasAuthority('ROLE_OAUTH_CLIENT_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/cliet/details")
    public ModelAndView delOauthClientDetails(@RequestParam("clientId") String clientId) {
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        try {
            boolean flag = authServiceClient.delOauthClientDetails(clientId);

            resultVO = CommonUtil.getResultVO(true, 0, "删除成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
