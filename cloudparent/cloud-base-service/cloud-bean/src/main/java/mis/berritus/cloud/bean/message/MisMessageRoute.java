package mis.berritus.cloud.bean.message;

import mis.berritus.cloud.bean.base.MisBean;

public class MisMessageRoute extends MisBean {
    private static final long serialVersionUID = -6342941725526957793L;

    private String exchangeName;
    private String routintKey;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutintKey() {
        return routintKey;
    }

    public void setRoutintKey(String routintKey) {
        this.routintKey = routintKey;
    }

    @Override
    public String toString() {
        return "MisMessageRoute{" +
                "exchangeName='" + exchangeName + '\'' +
                ", routintKey='" + routintKey + '\'' +
                '}';
    }
}
