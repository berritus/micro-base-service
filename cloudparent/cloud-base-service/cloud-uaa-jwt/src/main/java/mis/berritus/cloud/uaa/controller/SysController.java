package mis.berritus.cloud.uaa.controller;

import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserRole;
import mis.berritus.cloud.uaa.service.SysService;
import mis.berritus.cloud.uaa.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private SysService sysService;
    @Autowired
    private SysUserServiceImpl sysUserService;

    @PostMapping("/role/add")
    @PreAuthorize("hasAuthority('ROLE_USER_SUPER_ADMIN')")
    public int insertSysRole(@RequestBody SysRole record){
        SysUser sysUser = new SysUser();
        sysUser.setCrtDate(new Date());
        sysUser.setUserName("qingh");
        sysUser.setPassword("q123456");
        sysUser.setState(0);
        sysUserService.insert(sysUser);
        return sysService.insertSysRole(record);
    }

    @PostMapping("/userRole/add")
    @PreAuthorize("hasAuthority('ROLE_USER_SUPER_ADMIN')")
    public int insertSysUserRole(@RequestBody SysUserRole record){
        return sysService.insertSysUserRole(record);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER_SUPER_ADMIN')")
    public String sysIndex(){
        return "test auth sys";
    }
}
