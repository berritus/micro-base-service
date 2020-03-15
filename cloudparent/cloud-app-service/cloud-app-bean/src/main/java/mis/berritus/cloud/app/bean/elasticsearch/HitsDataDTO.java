package mis.berritus.cloud.app.bean.elasticsearch;

import com.berritus.mis.core.bean.MisBean;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/2
 */
public class HitsDataDTO extends MisBean {
    private static final long serialVersionUID = 727623752669740288L;

    private String _id;
    private String _index;
    private String _score;
    private String _type;
    private Object _source;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public void set_source(String _source) {
        this._source = _source;
    }

    public String get_score() {
        return _score;
    }

    public void set_score(String _score) {
        this._score = _score;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }
}
