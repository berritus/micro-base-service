package mis.berritus.cloud.app.bean.elasticsearch;

import mis.berritus.cloud.bean.base.MisBean;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/28
 */
public class ShardsDTO extends MisBean {
    private static final long serialVersionUID = 4634349669350603860L;
    private Integer failed;
    private Integer successful;
    private Integer total;
    private Integer skipped;

    public Integer getSkipped() {
        return skipped;
    }

    public void setSkipped(Integer skipped) {
        this.skipped = skipped;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public Integer getSuccessful() {
        return successful;
    }

    public void setSuccessful(Integer successful) {
        this.successful = successful;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
