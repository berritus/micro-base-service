package mis.berritus.cloud.app.bean.elasticsearch;

import mis.berritus.cloud.bean.base.MisBean;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/3
 */
public class QueryDTO extends MisBean {
    private static final long serialVersionUID = 5708837250303797081L;
    private Object match;

    public Object getMatch() {
        return match;
    }

    public void setMatch(Object match) {
        this.match = match;
    }
}
