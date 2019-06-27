package mis.berritus.cloud.app.bean.elasticsearch;

import mis.berritus.cloud.bean.service.cust.MisCustBase;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/28
 */
public class MisCustBaseExt extends MisCustBase {
    private static final long serialVersionUID = 1757693359312209536L;
    private String esIndex;
    private String esType;

    public String getEsIndex() {
        return esIndex;
    }

    public void setEsIndex(String esIndex) {
        this.esIndex = esIndex;
    }

    public String getEsType() {
        return esType;
    }

    public void setEsType(String esType) {
        this.esType = esType;
    }
}
