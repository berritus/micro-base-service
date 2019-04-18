package mis.berritus.cloud.service.sonar.service;

import mis.berritus.cloud.app.bean.sonar.ResultBean;

import java.io.OutputStream;

public interface SonarService {
    ResultBean getSonarData(String keys, String sonarHostUrl, String type);

    ResultBean getSonarIssuesData(String componentKeys, String types,
                                  Integer pages, Integer pageSize, String sonarHostUrl);

    void analysisDate(String title, String projs, OutputStream outputStream);

    void issuesSearch(String title, String projs, String types, OutputStream outputStream);
}
