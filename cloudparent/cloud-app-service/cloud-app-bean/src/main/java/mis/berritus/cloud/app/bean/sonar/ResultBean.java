package mis.berritus.cloud.app.bean.sonar;

import com.alibaba.fastjson.annotation.JSONField;
import mis.berritus.cloud.bean.base.MisBean;

import java.util.List;

public class ResultBean extends MisBean {
    private static final long serialVersionUID = 4228463807810285786L;

    private Long total;
    private Integer p;
    private Integer ps;

    @JSONField(name="measures")
    private List<Measures> measures;
    @JSONField(name="issues")
    private List<IssuesBean> issues;

    public List<Measures> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measures> measures) {
        this.measures = measures;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public List<IssuesBean> getIssues() {
        return issues;
    }

    public void setIssues(List<IssuesBean> issues) {
        this.issues = issues;
    }
}
