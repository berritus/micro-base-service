package mis.berritus.cloud.service.sonar.controller;

import com.alibaba.fastjson.JSON;
import mis.berritus.cloud.app.bean.sonar.Measures;
import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.app.common.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SonarController {

    @GetMapping("")
    public ResultBean index(){
        Map<String, String> headers = new HashMap<>();
        String url = "http://localhost:9000/api/measures/search?projectKeys=mdproj," +
                "comproj&metricKeys=alert_status,bugs,reliability_rating,vulnerabilities," +
                "security_rating,code_smells,sqale_rating,duplicated_lines_density,coverage,ncloc,ncloc_language_distribution";
        String str = HttpUtils.doGet(url, headers, null);

        //List<Measures> list =  JSON.parseArray(str, Measures.class);
        //ResultBean resultBean = new ResultBean();
        ResultBean resultBean = JSON.parseObject(str, ResultBean.class);
        return resultBean;
    }
}
