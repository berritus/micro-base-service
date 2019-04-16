package mis.berritus.cloud.service.sonar.controller;

import com.alibaba.fastjson.JSON;
import mis.berritus.cloud.app.bean.sonar.Measures;
import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.app.common.component.ExcelService;
import mis.berritus.cloud.app.common.utils.HttpUtils;
import mis.berritus.cloud.app.common.utils.SonarValueUtil;
import mis.berritus.cloud.service.sonar.service.SonarService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class SonarController {
    private Logger logger = LoggerFactory.getLogger(SonarController.class);

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

    //http://localhost:8107/sonar/analysis_date?projs=saas0319
    @GetMapping("/sonar/analysis_date")
    public void exportAnalysisDate(HttpServletResponse response, HttpServletRequest request){
        try{
            String projs = request.getParameter("projs");
            if(StringUtils.isEmpty(projs)){
                return;
            }

            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" +
                    new String("项目代码质量分析".getBytes("utf-8"),"ISO-8859-1" ) + ".xls");
            response.flushBuffer();
            sonarService.analysisDate("总概况", projs, response.getOutputStream());
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    // http://localhost:8107/sonar/issues/search?proj=saas0319
    @GetMapping("/sonar/issues/search")
    public void exportIssuesSearch(HttpServletResponse response, HttpServletRequest request){
        try{
            String proj = request.getParameter("proj");

            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" +
                    new String("项目代码质量分析".getBytes("utf-8"),"ISO-8859-1" ) + ".xls");
            response.flushBuffer();
            sonarService.issuesSearch("总概况", proj, response.getOutputStream());
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }
}
