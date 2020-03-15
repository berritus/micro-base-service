package mis.berritus.cloud.app.bean.common;

import com.berritus.mis.core.bean.MisBean;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/1
 */
public class PeopleInfoDTO extends MisBean {
    private static final long serialVersionUID = 5225591236154300694L;
    private String name;
    /**
     * 1--男 2--女
     */
    private Integer sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
