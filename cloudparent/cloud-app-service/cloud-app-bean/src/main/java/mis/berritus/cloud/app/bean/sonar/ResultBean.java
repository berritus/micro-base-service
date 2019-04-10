package mis.berritus.cloud.app.bean.sonar;

import com.alibaba.fastjson.annotation.JSONField;
import mis.berritus.cloud.bean.base.MisBean;

import java.util.List;

public class ResultBean extends MisBean {
    private static final long serialVersionUID = 4228463807810285786L;

    @JSONField(name="measures")
    private List<Measures> measures;

    public List<Measures> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measures> measures) {
        this.measures = measures;
    }
}
