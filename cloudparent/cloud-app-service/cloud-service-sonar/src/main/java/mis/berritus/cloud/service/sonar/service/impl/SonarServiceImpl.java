package mis.berritus.cloud.service.sonar.service.impl;

import mis.berritus.cloud.app.bean.sonar.IssuesBean;
import mis.berritus.cloud.app.bean.sonar.Measures;
import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.app.common.component.ExcelService;
import mis.berritus.cloud.app.common.utils.SonarValueUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SonarServiceImpl extends AbstractSonarService {
    private Logger logger = LoggerFactory.getLogger(SonarServiceImpl.class);

    @Value("${sonar.service.url}")
    private String sonarHostUrl;

    @Autowired
    private ExcelService excelService;

    @Override
    public void analysisDate(String title, String projs, OutputStream outputStream) {
        List<String> header = new ArrayList<>();
        header.add("项目");

        ResultBean resultBean = getSonarData(projs, sonarHostUrl, "analysis_date");
        if(resultBean == null){
            return;
        }

        List<Measures> measures = resultBean.getMeasures();
        String[] projArray = projs.split(",");

        Map<String, List<String>> dataMap = getExcelDataMap(projArray);

        String firstProj = "";
        List<String> filterData = new ArrayList<>();

        for(Measures bean : measures){
            if(StringUtils.isEmpty(firstProj)){
                firstProj = bean.getComponent();
            }

            if(firstProj.equals(bean.getComponent())){
                String metricValue = SonarValueUtil.getMetric(bean.getMetric());
                if(StringUtils.isEmpty(metricValue)){
                    filterData.add(bean.getMetric());
                    continue;
                }

                header.add(metricValue);
                setExcelCellData(bean.getComponent(), bean.getValue(), dataMap);
            }else{
                for(int i = 0; i < projArray.length; i++){
                    if(projArray[i].equals(bean.getComponent())){
                        if(filterData.contains(bean.getMetric())){
                            continue;
                        }
                        setExcelCellData(bean.getComponent(), bean.getValue(), dataMap);
                    }
                }
            }
        }

        try {
            excelService.exportList(title, header, dataMap, outputStream);
        }catch (Exception e){
            logger.error("" + e.getMessage());
        }
    }

    @Override
    public void issuesSearch(String title, String projs, OutputStream outputStream) {
        List<String> header = new ArrayList<>();
        header.add("哈希值");
        header.add("文件路径");
        header.add("问题");
        header.add("行数");

        ResultBean resultBean = getSonarData(projs, sonarHostUrl, "issues_search");
        if(resultBean == null){
            return;
        }

        List<IssuesBean> issues = resultBean.getIssues();
        if(issues == null || issues.size() == 0){
            return;
        }

        Map<String, List<String>> dataMap = new HashMap<>();
        for(IssuesBean issuesBean : issues){
            List<String> data = new ArrayList<>();
            data.add(issuesBean.getHash());
            data.add(issuesBean.getComponent());
            data.add(issuesBean.getMessage());
            data.add(issuesBean.getLine() + "");
            dataMap.put(issuesBean.getKey(), data);
        }

        try {
            excelService.exportList(title, header, dataMap, outputStream);
        }catch (Exception e){
            logger.error("" + e.getMessage());
        }
    }
}
