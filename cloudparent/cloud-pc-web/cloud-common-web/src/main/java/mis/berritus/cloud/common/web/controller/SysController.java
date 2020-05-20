package mis.berritus.cloud.common.web.controller;

import com.berritus.mis.core.common.utils.MisDateUtil;
import com.berritus.mis.core.common.utils.MisRandUtil;
import com.berritus.mis.core.common.utils.MisStringUtil;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.app.bean.common.ResultVO;
import mis.berritus.cloud.app.common.utils.CommonUtil;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.bean.uaa.ext.SysUserRoleDTOExt;
import mis.berritus.cloud.common.web.feign.client.AuthServiceClient;
import mis.berritus.cloud.common.web.feign.client.SysServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/14
 */
@RestController
@RequestMapping("/sys")
public class SysController {
    private static final Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private SysServiceClient sysServiceClient;
    @Autowired
    private AuthServiceClient authServiceClient;

    // http://localhost:8110/layui/sys/param/add/page
//    @RequestMapping("/sys/param/add/page")
//    public String sysParamAdd(Model model) {
//        // model.addAttribute("message", "你好啊");
//        return "study/layui/system_param_add";
//    }
//
//    // http://localhost:8110/layui/sys/param/list/page
//    @RequestMapping("/sys/param/list/page")
//    public String sysParamList(Model model) {
//
//        return "study/layui/system_param_list";
//    }

    // http://localhost:8110/layui/sys/user/add/page
//    @RequestMapping("/sys/user/add/page")
//    public String sysUserAddPage(Model model) {
//        // model.addAttribute("message", "你好啊");
//        return "study/layui/user_add";
//    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN','sys:param:add')")
    @PostMapping("/param/add")
    public ModelAndView insertSysParam(@RequestBody SystemParam systemParam) {
        Map<String, Object> map = new HashMap<>(10);
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

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:param:view')")
    //@PreAuthorize("hasAnyAuthority('sys:param:view')")
    @GetMapping("/param/list")
    public ModelAndView listSysParams(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>(10);
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

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:param:del')")
    @GetMapping("/param/del")
    public ModelAndView delSysParam(String paramId) {
        Map<String, Object> map = new HashMap<>(10);
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

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:param:edit')")
    @PostMapping("/param/update")
    public ModelAndView updateSystemParam(@RequestBody SystemParam systemParam) {
        Map<String, Object> map = new HashMap<>(10);
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


    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:user:view')")
    @GetMapping("/user/list")
    public ModelAndView listSysUser(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        List<SysUserDTO> list = null;
        SysUserDTO record = new SysUserDTO();
        try {
            record.setState(0);
            record.setPageNum(page);
            record.setPageSize(limit);
            PageInfo<SysUserDTO> plist = authServiceClient.listSysUser(record);
            list = plist.getList();
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", plist.getTotal());
        } catch (Exception e) {
            logger.error("查询失败，{}，异常{}", record.toString(), e);
            map.put("code", -1);
            map.put("msg", "查询失败");
        }
        map.put("result", resultVO);
        map.put("data", list);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:user:add')")
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ModelAndView insertSysUser(@RequestBody SysUserDTO record){
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        try {
            SysUserDTO sysUser = authServiceClient.insertSysUser(record);
            map.put("data", sysUser);
            resultVO = CommonUtil.getResultVO(true, 0, "添加成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:user:view')")
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ModelAndView getSysUser(@RequestParam("userName") String userName){
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        try {
            SysUserDTO sysUser = authServiceClient.getSysUser(userName);
            map.put("data", sysUser);
            resultVO = CommonUtil.getResultVO(true, 0, "查找成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    // , @RequestBody SysUserDTO sysUserDTO
    //@PreAuthorize("hasAuthority('ROLE_USER_SUPER_ADMIN')" + " || hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:user:view')")
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ModelAndView listSysUsers(Integer page, Integer limit,
                                     Long seqId, String uuid, String userName, String mobilePhone,
                                     Integer state, Integer year){
        Map<String, Object> map = new HashMap<>(10);
        try {
            SysUserDTO record = new SysUserDTO();
            record.setState(state);
            record.setSeqId(seqId);
            record.setUuid(uuid);
            record.setUserName(userName);
            record.setMobilePhone(mobilePhone);
            record.setPageNum(page);
            record.setPageSize(limit);

            if (year != null && year != 0) {
                Date date1 = MisDateUtil.str2Date(year + "-01-01", "yyyy-MM-dd");
                record.setCrtDate(date1);

                year++;
                Date date2 = MisDateUtil.str2Date(year + "-01-01", "yyyy-MM-dd");
                record.setLimitDate(date2);
            }

            PageInfo<SysUserDTO> plist = authServiceClient.listSysUser(record);
            map.put("data", plist.getList());
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", 1);
        } catch (Exception e) {
            logger.error("失败，{}",e);
            map.put("code", -1);
            map.put("msg", "查询失败");
        }

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:user:del')")
    //@PreAuthorize("hasAuthority('ROLE_USER_SUPER_ADMIN')" + " || hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/user")
    public ModelAndView delSysUser(@RequestParam("seqId") Long seqId){
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        try {
            authServiceClient.delSysUser(seqId);
            resultVO = CommonUtil.getResultVO(true, 0, "删除成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:role:view')")
    @GetMapping("/role/list")
    public ModelAndView listSysRole(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>(10);
        SysRoleDTO record = new SysRoleDTO();
        try {
            record.setState((byte)0);
            record.setPageNum(page);
            record.setPageSize(limit);
            PageInfo<SysRoleDTO> plist = authServiceClient.listSysRole(record);
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", plist.getTotal());
            map.put("data", plist.getList());
        } catch (Exception e) {
            logger.error("查询失败，{}，异常{}", record.toString(), e);
            map.put("code", -1);
            map.put("msg", "查询失败");
        }

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:role:add')")
    @RequestMapping(method = RequestMethod.POST, value = "/role")
    public ModelAndView insertSysRole(@RequestBody SysRoleDTO record){
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        try {
            SysRoleDTO sysRole = authServiceClient.insertSysRole(record);
            map.put("data", sysRole);
            resultVO = CommonUtil.getResultVO(true, 0, "添加成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:role:del')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/role")
    public ModelAndView delSysRole(@RequestParam("seqId") Long seqId){
        Map<String, Object> map = new HashMap<>(10);
        ResultVO resultVO = null;
        try {
            authServiceClient.delSysRole(seqId);
            resultVO = CommonUtil.getResultVO(true, 0, "删除成功");
        } catch (Exception e) {
            logger.error("失败，{}",e);
            String errMsg = e.getMessage();
            resultVO = CommonUtil.getResultVO(false, -1, errMsg);
        }

        map.put("result", resultVO);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:user:role:view')")
    @GetMapping("/user/role/list")
    public ModelAndView listSysUserRole(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>(10);
        SysUserRoleDTOExt record = new SysUserRoleDTOExt();
        try {
            record.setState((byte)0);
            record.setPageNum(page);
            record.setPageSize(limit);
            PageInfo<SysUserRoleDTOExt> plist = authServiceClient.listSysUserRole(record);
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", plist.getTotal());
            map.put("data", plist.getList());
        } catch (Exception e) {
            logger.error("查询失败，{}，异常{}", record.toString(), e);
            map.put("code", -1);
            map.put("msg", "查询失败");
        }

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER_SUPER_ADMIN', 'ROLE_ADMIN', 'sys:user:role:view')")
    @RequestMapping(method = RequestMethod.GET, value = "/user/role")
    public ModelAndView listSysUserRole(Integer page, Integer limit,
                                        String uid, String rid, String userName,
                                        String roleName,String roleCode,
                                        Integer state, Integer year){
        Map<String, Object> map = new HashMap<>(10);
        try {
            SysUserRoleDTOExt record = new SysUserRoleDTOExt();
            record.setState((byte)state.intValue());
            record.setUid(uid);
            record.setRid(rid);
            record.setUserName(userName);
            record.setRoleCode(roleCode);
            record.setRoleName(roleName);
            record.setPageNum(page);
            record.setPageSize(limit);

            if (year != null && year != 0) {
                Date date1 = MisDateUtil.str2Date(year + "-01-01", "yyyy-MM-dd");
                record.setCrtDate(date1);

                year++;
                Date date2 = MisDateUtil.str2Date(year + "-01-01", "yyyy-MM-dd");
                record.setLimitDate(date2);
            }

            PageInfo<SysUserRoleDTOExt> plist = authServiceClient.listSysUserRole(record);
            map.put("data", plist.getList());
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", 1);
        } catch (Exception e) {
            logger.error("失败，{}",e);
            map.put("code", -1);
            map.put("msg", "查询失败");
        }

        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
