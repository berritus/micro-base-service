package mis.berritus.cloud.uaa.controller;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.ext.SysUserRoleDTOExt;
import mis.berritus.cloud.uaa.service.impl.SysRoleServiceImpl;
import mis.berritus.cloud.uaa.service.impl.SysUserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/20
 */
@RestController
@RequestMapping("/sys/user/role")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleServiceImpl sysUserRoleService;


    @PostMapping("/list")
    public PageInfo<SysUserRoleDTOExt> listSysUserRole(@RequestBody SysUserRoleDTOExt record) {
        return sysUserRoleService.listSysUserRole(record);
    }
}
