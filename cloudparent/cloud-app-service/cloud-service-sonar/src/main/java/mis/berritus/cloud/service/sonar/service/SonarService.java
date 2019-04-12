package mis.berritus.cloud.service.sonar.service;

import mis.berritus.cloud.app.bean.sonar.ResultBean;

public interface SonarService {
    public ResultBean getSonarData(String projectKeys);
}
