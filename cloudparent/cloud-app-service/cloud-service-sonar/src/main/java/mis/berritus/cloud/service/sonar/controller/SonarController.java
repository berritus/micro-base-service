package mis.berritus.cloud.service.sonar.controller;

import com.alibaba.fastjson.JSON;
import mis.berritus.cloud.app.bean.sonar.Measures;
import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.app.common.component.ExcelService;
import mis.berritus.cloud.app.common.utils.HttpUtils;
import mis.berritus.cloud.app.common.utils.SonarValueUtil;
import mis.berritus.cloud.service.sonar.service.SonarService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SonarController {
    @Autowired
    private ExcelService excelService;
    @Autowired
    private SonarService sonarService;

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

    //http://localhost:8107/sonar/analysis_date
    @GetMapping("/sonar/analysis_date")
    public void exportAnalysisDate(HttpServletResponse response){
        try{
            List<String> header = new ArrayList<>();
            //header.add("1111");
            Map<Integer, List<String>> dataMap = new HashMap<>();
            //dataMap.put(0, header);

            ResultBean resultBean = sonarService.getSonarData("mdproj,comproj");
            if(resultBean == null){
                return;
            }

            List<Measures> measures = resultBean.getMeasures();
            List<String> datas = new ArrayList<>();
            List<String> datas2 = new ArrayList<>();
            for(Measures bean : measures){
                if("comproj".equals(bean.getComponent())){
                    header.add(SonarValueUtil.getMetric(bean.getMetric()));
                    datas.add(bean.getValue());
                }else if("mdproj".equals(bean.getComponent())){
                    datas2.add(bean.getValue());
                }
            }

            dataMap.put(0, datas);
            dataMap.put(1, datas2);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=student.xls");
            response.flushBuffer();
            excelService.exportList("hello", header, dataMap, response.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
