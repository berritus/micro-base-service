package mis.berritus.cloud.bean.uaa.ext;

import mis.berritus.cloud.bean.uaa.SysUserRoleDTO;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/20
 */
public class SysUserRoleDTOExt extends SysUserRoleDTO {
    private static final long serialVersionUID = 6238727863326667091L;

    private String userName;

    private String roleName;

    private String roleCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
