package mis.berritus.cloud.uaa.service;


import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.bean.uaa.SysUserRoleDTO;

public interface SysService {
    int insertSysRole(SysRoleDTO record);
    long insertSysUserRole(SysUserRoleDTO record);
    SysUserDTO matchesUser(String username, String password);
}
