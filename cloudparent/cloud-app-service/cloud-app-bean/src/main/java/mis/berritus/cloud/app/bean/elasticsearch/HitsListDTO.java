package mis.berritus.cloud.app.bean.elasticsearch;

import mis.berritus.cloud.bean.base.MisBean;

import java.util.List;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/2
 */
public class HitsListDTO extends MisBean {
    private static final long serialVersionUID = -7971943354539163857L;
    private List<HitsDataDTO> hits;
    private String max_score;
    private Integer total;

    public List<HitsDataDTO> getHits() {
        return hits;
    }

    public void setHits(List<HitsDataDTO> hits) {
        this.hits = hits;
    }
}
