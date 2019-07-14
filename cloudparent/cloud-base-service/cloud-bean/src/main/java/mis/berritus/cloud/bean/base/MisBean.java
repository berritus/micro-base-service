package mis.berritus.cloud.bean.base;

import java.io.Serializable;

public class MisBean implements Serializable {
    private static final long serialVersionUID = 4883078674496741278L;

    /**
     * 每页数
     */
    private Integer pageSize;
    /**
     * 当前页数
     */
    private Integer pageNum;

    private Integer total;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
