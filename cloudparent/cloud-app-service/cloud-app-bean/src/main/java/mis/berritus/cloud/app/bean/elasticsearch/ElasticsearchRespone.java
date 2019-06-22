package mis.berritus.cloud.app.bean.elasticsearch;

import mis.berritus.cloud.bean.base.MisBean;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
public class ElasticsearchRespone extends MisBean {
    private static final long serialVersionUID = -4607010544980550718L;

    private boolean acknowledged;
    private boolean shardsAcknowledged;

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public boolean isShardsAcknowledged() {
        return shardsAcknowledged;
    }

    public void setShardsAcknowledged(boolean shardsAcknowledged) {
        this.shardsAcknowledged = shardsAcknowledged;
    }
}
