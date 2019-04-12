package mis.berritus.cloud.service.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.app.common.utils.HttpUtils;
import mis.berritus.cloud.service.sonar.constant.SonarConstant;
import mis.berritus.cloud.service.sonar.service.SonarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SonarServiceImpl implements SonarService {
    @Value("${sonar.service.url}")
    private String sonarHostUrl;

    @Override
    public ResultBean getSonarData(String projectKeys) {
        Map<String, String> headers = new HashMap<>();
        String url = SonarConstant.API_MEASURES_SEARCH;

        url = sonarHostUrl + url.replace("${}", projectKeys);
        String str = HttpUtils.doGet(url, headers, null);
        ResultBean resultBean = JSON.parseObject(str, ResultBean.class);
        return resultBean;
    }
}
