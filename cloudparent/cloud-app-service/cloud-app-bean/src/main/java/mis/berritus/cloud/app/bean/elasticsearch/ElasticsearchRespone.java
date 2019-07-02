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
    private String _id;
    private String _index;
    private Integer _primary_term;
    private Integer _seq_no;
    private String _type;
    private Integer _version;
    private String result;
    private ShardsDTO _shards;
    private boolean timed_out;
    private Integer took;
    private HitsListDTO hits;

    public HitsListDTO getHits() {
        return hits;
    }

    public void setHits(HitsListDTO hits) {
        this.hits = hits;
    }

    public boolean isTimed_out() {
        return timed_out;
    }

    public void setTimed_out(boolean timed_out) {
        this.timed_out = timed_out;
    }

    public Integer getTook() {
        return took;
    }

    public void setTook(Integer took) {
        this.took = took;
    }

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

    public Integer get_primary_term() {
        return _primary_term;
    }

    public void set_primary_term(Integer _primary_term) {
        this._primary_term = _primary_term;
    }

    public Integer get_seq_no() {
        return _seq_no;
    }

    public void set_seq_no(Integer _seq_no) {
        this._seq_no = _seq_no;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public Integer get_version() {
        return _version;
    }

    public void set_version(Integer _version) {
        this._version = _version;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ShardsDTO get_shards() {
        return _shards;
    }

    public void set_shards(ShardsDTO _shards) {
        this._shards = _shards;
    }
}
