package mis.berritus.cloud.service.sonar.service.impl;

import mis.berritus.cloud.app.bean.excel.ExportDataBean;
import mis.berritus.cloud.app.bean.sonar.IssuesBean;
import mis.berritus.cloud.app.bean.sonar.Measures;
import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.app.common.component.ExcelService;
import mis.berritus.cloud.app.common.utils.SonarValueUtil;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.service.sonar.constant.SonarConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        header.add("项目名");

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
    public void issuesSearch(String title, String projs, String types, OutputStream outputStream) {
        List<String> header = new ArrayList<>();
        header.add("编号");
        header.add("文件路径");
        header.add("问题");
        header.add("行数");

        Map<String, List<String>> dataMap = new HashMap<>();
        boolean finishFlag = false;
        long count = 0;

        int pagesNum = 0;

        while(!finishFlag){
            pagesNum++;
            Page page = new Page();
            page.setPageNum(pagesNum);
            page.setPageSize(SonarConstant.DEFAULT_PAGE_SIZE);
            ResultBean resultBean = getSonarIssuesData(projs,types, page, sonarHostUrl, false);
            if(resultBean == null){
                break;
            }

            Long total = resultBean.getTotal();
            if(total == null){
                return;
            }

            List<IssuesBean> issues = resultBean.getIssues();
            if(issues == null || issues.size() == 0){
                break;
            }

            for(IssuesBean issuesBean : issues){
                List<String> data = new ArrayList<>();
                data.add(issuesBean.getHash());
                data.add(issuesBean.getComponent());
                data.add(issuesBean.getMessage());
                data.add(issuesBean.getLine() + "");
                dataMap.put(issuesBean.getKey(), data);
            }

            count += issues.size();
            if(count >= total){
                finishFlag = true;
            }
        }

        try {
            excelService.exportList(title, header, dataMap, outputStream);
        }catch (Exception e){
            logger.error("" + e.getMessage());
        }
    }

    @Override
    public void issuesSearchForAllProj(String[] projs, String types, boolean isNew, OutputStream outputStream) {

        List<ExportDataBean> listData = new ArrayList<>();

        if (projs == null || projs.length == 0) {
            return;
        }

        for (String projName : projs) {
            ExportDataBean exportDataBean = new ExportDataBean();
            exportDataBean.setSheetName(projName);

            List<String> header = new ArrayList<>();
            header.add("类型");
            header.add("编号");
            header.add("文件路径");
            header.add("问题");
            header.add("行数");
            header.add("备注");
            header.add("处理人");

            exportDataBean.setHeaders(header);

            Map<String, List<String>> dataMap = new HashMap<>();
            boolean finishFlag = false;
            long count = 0;

            int pagesNum = 0;

            while(!finishFlag){
                pagesNum++;
                Page page = new Page();
                page.setPageNum(pagesNum);
                page.setPageSize(SonarConstant.DEFAULT_PAGE_SIZE);

                ResultBean resultBean = getSonarIssuesData(projName,types, page, sonarHostUrl, isNew);
                if(resultBean == null){
                    break;
                }

                Long total = resultBean.getTotal();
                if(total == null){
                    return;
                }

                String typeStr = "";
                if ("BUG".equals(types)) {
                    typeStr = "BUG";
                } else if ("VULNERABILITY".equals(types)) {
                    typeStr = "漏洞";
                }

                List<IssuesBean> issues = resultBean.getIssues();
                if(issues == null || issues.size() == 0){
                    break;
                }

                for(IssuesBean issuesBean : issues){
                    List<String> data = new ArrayList<>();
                    data.add(typeStr);
                    data.add(issuesBean.getHash());
                    data.add(issuesBean.getComponent());
                    data.add(issuesBean.getMessage());
                    data.add(issuesBean.getLine() + "");
                    dataMap.put(issuesBean.getKey(), data);
                }

                count += issues.size();
                if(count >= total){
                    finishFlag = true;
                }
            }

            exportDataBean.setDataMap(dataMap);

            listData.add(exportDataBean);
        }

        try {
            excelService.exportListForSheet(listData, outputStream);
        }catch (Exception e){
            logger.error("" + e.getMessage());
        }
    }
}
