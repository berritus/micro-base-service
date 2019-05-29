package mis.berritus.cloud.service.sonar.service;

import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.bean.base.Page;

import java.io.OutputStream;
import java.util.List;

public interface SonarService {
    /**
     * @Description:
     * @Author: Qin Guihe
     * @Create: 2019/5/27
     * @return: 
     */
    ResultBean getSonarData(String keys, String sonarHostUrl, String type);
    /**
     * @Description:
     * @Author: Qin Guihe
     * @Create: 2019/5/27
     * @return: 
     */
    ResultBean getSonarIssuesData(String componentKeys, String types, Page page, String sonarHostUrl, boolean isNew);
    /**
     * @Description:
     * @Author: Qin Guihe
     * @Create: 2019/5/27
     * @return: 
     */
    void analysisDate(String title, String projs, OutputStream outputStream);
    /**
     * @Description:
     * @Author: Qin Guihe
     * @Create: 2019/5/27
     * @return: 
     */
    void issuesSearch(String title, String projs, String types, OutputStream outputStream);

    /**
     * @Description:
     * @Author: Qin Guihe
     * @Create: 2019/5/27
     * @return: 
     */
    void issuesSearchForAllProj(String[] projs, String types, boolean isNew, OutputStream outputStream);
}
