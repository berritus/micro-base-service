package mis.berritus.cloud.bean.sys.service;

import mis.berritus.cloud.bean.base.MisBean;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/15
 */
public class LoginInfo extends MisBean {
    private static final long serialVersionUID = -861958000405558664L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
