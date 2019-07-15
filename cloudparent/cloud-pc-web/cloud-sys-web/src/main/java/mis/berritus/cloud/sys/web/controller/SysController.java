package mis.berritus.cloud.sys.web.controller;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.app.bean.common.ResultVO;
import mis.berritus.cloud.app.common.utils.CommonUtil;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.web.feign.client.SysServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @Date: 2019/7/14
 */
@Controller
@RequestMapping("/layui")
public class SysController {
    private static final Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private SysServiceClient sysServiceClient;

    // http://localhost:8110/layui/sys/param/add/page
    @RequestMapping("/sys/param/add/page")
    public String sysParamAdd(Model model) {
        // model.addAttribute("message", "你好啊");
        return "study/layui/system_param_add";
    }

    // http://localhost:8110/layui/sys/param/list/page
    @RequestMapping("/sys/param/list/page")
    public String sysParamList(Model model) {

        return "study/layui/system_param_list";
    }

    // http://localhost:8110/layui/sys/user/add/page
    @RequestMapping("/sys/user/add/page")
    public String sysUserAddPage(Model model) {
        // model.addAttribute("message", "你好啊");
        return "study/layui/user_add";
    }

    @PostMapping("/sys/param/add")
    @ResponseBody
    public ModelAndView insertSysParam(@RequestBody SystemParam systemParam) {
        Map<String, Object> map = new HashMap<>();
        ResultVO resultVO = null;
        try {
            SystemParam systemParam1 = sysServiceClient.insertSysParam(systemParam);
            if (systemParam1 != null) {
                resultVO = CommonUtil.getResultVO(true, 0, "成功");
            } else {
                resultVO = CommonUtil.getResultVO(false, 0, "添加失败");
            }
        } catch (RuntimeException e) {
            logger.error("添加失败，{}，异常{}", systemParam.toString(), e);
            resultVO = CommonUtil.getResultVO(false, 0, e.getMessage());
        } catch (Exception e) {
            logger.error("添加失败，{}，异常{}", systemParam.toString(), e);
            resultVO = CommonUtil.getResultVO(false, 0, "系统错误");
        }

        map.put("result", resultVO);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @GetMapping("/sys/param/list")
    @ResponseBody
    public ModelAndView listSysParams(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        ResultVO resultVO = null;
        List<SystemParam> list = null;
        SystemParam systemParam = new SystemParam();

        try {
            systemParam.setState((byte)0);
            systemParam.setPageNum(page);
            systemParam.setPageSize(limit);
            PageInfo<SystemParam> plist = sysServiceClient.listSysParams(systemParam);
            list = plist.getList();
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", plist.getTotal());
        } catch (Exception e) {
            logger.error("查询失败，{}，异常{}", systemParam.toString(), e);
            map.put("code", -1);
            map.put("msg", "查询失败");
        }
        map.put("result", resultVO);
        map.put("data", list);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @GetMapping("/sys/param/del")
    public ModelAndView delSysParam(String paramId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer ret = sysServiceClient.delSysParam(paramId);
            if (ret == null) {
                map.put("code", -1);
                map.put("msg", "删除失败");
            } else {
                map.put("code", ret);
                map.put("msg", "删除成功");
            }
        } catch (Exception e) {
            logger.error("删除失败，异常{}", e);
            map.put("code", -1);
            map.put("msg", "删除失败");
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @PostMapping("/sys/param/update")
    public ModelAndView updateSystemParam(@RequestBody SystemParam systemParam) {
        Map<String, Object> map = new HashMap<>();
        try {
            SystemParam ret = sysServiceClient.updateSystemParam(systemParam);
            if (ret == null) {
                map.put("code", -1);
                map.put("msg", "更新失败");
            } else {
                map.put("code", 0);
                map.put("msg", "更新成功");
            }
        } catch (Exception e) {
            logger.error("更新失败，异常{}", e);
            map.put("code", -1);
            map.put("msg", "更新失败");
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
