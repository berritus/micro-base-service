package mis.berritus.cloud.uaa.service;


import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserRole;

public interface SysService {
    int insertSysRole(SysRole record);
    int insertSysUserRole(SysUserRole record);
    SysUser matchesUser(String username, String password);
}
