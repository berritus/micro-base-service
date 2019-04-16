package mis.berritus.cloud.service.sonar.service;

import mis.berritus.cloud.app.bean.sonar.ResultBean;

import java.io.OutputStream;

public interface SonarService {
    ResultBean getSonarData(String keys, String sonarHostUrl, String type);

    void analysisDate(String title, String projs, OutputStream outputStream);
    //issues/search
    void issuesSearch(String title, String projs, OutputStream outputStream);
}
