package mis.berritus.cloud.uaa.controller;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.uaa.service.AuthService;
import mis.berritus.cloud.uaa.service.impl.SysRoleServiceImpl;
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
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private AuthService authService;
    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @PostMapping("/list")
    public PageInfo<SysRoleDTO> listSysRole(@RequestBody SysRoleDTO record) {
        return sysRoleService.listSysRole(record);
    }
}
