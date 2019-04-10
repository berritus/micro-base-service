package mis.berritus.cloud.service.sonar.component;

import mis.berritus.cloud.app.common.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class SonarService {
    public void str(){
        Map<String, String> headers = new HashMap<>();
        String url = "http://localhost:9000/api/measures/search?projectKeys=mdproj," +
                "comproj&metricKeys=alert_status,bugs,reliability_rating,vulnerabilities," +
                "security_rating,code_smells,sqale_rating,duplicated_lines_density,coverage,ncloc,ncloc_language_distribution";
        String str = HttpUtils.doGet(url);
        System.out.println(str);
    }
}
