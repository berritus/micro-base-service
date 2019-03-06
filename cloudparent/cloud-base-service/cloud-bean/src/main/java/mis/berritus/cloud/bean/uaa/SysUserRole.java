package mis.berritus.cloud.bean.uaa;

import java.io.Serializable;
import java.util.Date;

public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 6592084428279950335L;
    private Integer id;

    private Integer uid;

    private Integer rid;

    private Date crtDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }
}