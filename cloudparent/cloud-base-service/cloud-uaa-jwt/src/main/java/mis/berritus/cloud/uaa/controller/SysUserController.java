package mis.berritus.cloud.uaa.controller;

import com.berritus.mis.core.common.utils.MisRandUtil;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.uaa.service.AuthService;
import mis.berritus.cloud.uaa.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/17
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private AuthService authService;

    @PostMapping("/list")
    public PageInfo<SysUserDTO> listSysUser(@RequestBody SysUserDTO record) {
       return sysUserService.listSysUser(record);
    }

    @PostMapping("/add")
    public SysUserDTO insertSysUser(@RequestBody SysUserDTO record){
        String uuid = MisRandUtil.uuid();
        record.setUuid(uuid);
        record.setCrtDate(new Date());
        record.setStateDate(new Date());
        record.setState(0);
        record.setPassword(authService.encryptionPassword(record.getPassword()));
        sysUserService.insert(record);
        return record;
    }

//    @PreAuthorize("hasAuthority('ROLE_OAUTH_CLIENT_ADMIN')")
//    @RequestMapping(method = RequestMethod.DELETE, value = "/cliet/details")
//    public ModelAndView delOauthClientDetails(@RequestParam("clientId") String clientId) {
//
//    }

    @GetMapping("/get")
    public SysUserDTO getSysUser(@RequestParam("userName") String userName){
        return sysUserService.selectByUserName(userName);
    }

    @PostMapping("/del")
    public void delSysUser(@RequestParam("seqId") Long seqId){
        sysUserService.deleteByPrimaryKey(seqId);
    }
}
