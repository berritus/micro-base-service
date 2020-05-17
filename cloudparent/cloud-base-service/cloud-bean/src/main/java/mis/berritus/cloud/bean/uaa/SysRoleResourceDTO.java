package mis.berritus.cloud.bean.uaa;

import com.berritus.mis.core.bean.MisBean;

import java.io.Serializable;
import java.util.Date;

public class SysRoleResourceDTO extends MisBean {
    private static final long serialVersionUID = -6973543764733672040L;
    private Long seqId;

    private String uuid;

    private String rid;

    private String resId;

    private Byte state;

    private Date stateDate;

    private Date crtDate;

    private String applicationCode;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode == null ? null : applicationCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seqId=").append(seqId);
        sb.append(", uuid=").append(uuid);
        sb.append(", rid=").append(rid);
        sb.append(", resId=").append(resId);
        sb.append(", state=").append(state);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", applicationCode=").append(applicationCode);
        sb.append("]");
        return sb.toString();
    }
}