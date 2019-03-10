package mis.berritus.cloud.app.bean.uaa;

import mis.berritus.cloud.bean.base.MisBean;
import mis.berritus.cloud.bean.uaa.SysUser;

public class SysUserExt extends MisBean {
    private static final long serialVersionUID = 2416564346658975203L;

    private SysUser sysUser;
    private MisJwt misJwt;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public MisJwt getMisJwt() {
        return misJwt;
    }

    public void setMisJwt(MisJwt misJwt) {
        this.misJwt = misJwt;
    }
}
