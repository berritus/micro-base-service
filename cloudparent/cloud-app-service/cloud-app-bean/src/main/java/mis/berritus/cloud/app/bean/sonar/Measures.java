package mis.berritus.cloud.app.bean.sonar;

import com.berritus.mis.core.bean.MisBean;

public class Measures extends MisBean {
    private static final long serialVersionUID = 8920275255858982277L;

    private String component;
    private String metric;
    private String value;
    private boolean bestValue;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isBestValue() {
        return bestValue;
    }

    public void setBestValue(boolean bestValue) {
        this.bestValue = bestValue;
    }
}
