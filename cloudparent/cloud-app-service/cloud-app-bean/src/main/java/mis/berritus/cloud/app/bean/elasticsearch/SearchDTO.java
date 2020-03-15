package mis.berritus.cloud.app.bean.elasticsearch;

import com.berritus.mis.core.bean.MisBean;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/3
 */
public class SearchDTO extends MisBean {
    private static final long serialVersionUID = 2759079293543994884L;

    private Object query;
    private Integer size;

    public Object getQuery() {
        return query;
    }

    public void setQuery(Object query) {
        this.query = query;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
