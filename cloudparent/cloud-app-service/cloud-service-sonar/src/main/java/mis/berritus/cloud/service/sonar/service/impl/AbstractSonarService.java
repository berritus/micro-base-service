package mis.berritus.cloud.service.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.berritus.mis.core.component.utils.HttpUtil;
import mis.berritus.cloud.app.bean.sonar.ResultBean;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.service.sonar.constant.SonarConstant;
import mis.berritus.cloud.service.sonar.service.SonarService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: cloud-parent
 * @Copyright
 * @Author: Qin Guihe
 * @Create: 2019-04-16 10:32
 * @Description:
 */
public abstract class AbstractSonarService implements SonarService {

	/**
	 * @Description:
	 * @Author: Qin Guihe
	 * @Create: 2019/5/27
	 * @return: 
	 */
	public ResultBean getSonarData(String keys, String sonarHostUrl, String type) {
		Map<String, String> headers = new HashMap<>();
		String url = "";
		if("analysis_date".equals(type)){
			url = SonarConstant.API_MEASURES_SEARCH;
		}else if("issues_search".equals(type)){
			//url = SonarConstant.API_ISSUES_SEARCH;
		}

		url = sonarHostUrl + url.replace("${}", keys);
		String str = HttpUtil.get(url, headers, null);
		ResultBean resultBean = JSON.parseObject(str, ResultBean.class);
		return resultBean;
	}


	/**
	 * @Description:
	 * @Author: Qin Guihe
	 * @Create: 2019/5/27
	 * @return: 
	 */
	public ResultBean getSonarIssuesData(String componentKeys, String types, Page page, String sonarHostUrl,
										 boolean isNew){
		Map<String, String> headers = new HashMap<>();
		String url = sonarHostUrl + "/api/issues/search?componentKeys=" + componentKeys
				+ "&s=FILE_LINE&resolved=false&types="+ types + "&ps=" + page.getPageSize()
				+ "&organization=default-organization&p=" + page.getPageNum() + "&additionalFields=_all";

		if (isNew) {
			url += "&facets=severities,types&sinceLeakPeriod=true";
		}

		String str = HttpUtil.get(url, headers, null);
		ResultBean resultBean = JSON.parseObject(str, ResultBean.class);
		return resultBean;
	}
	
	/**
	 * 生成所有项目的数据存储区域
	 * @param projArray
	 * @return
	 */
	protected Map<String, List<String>> getExcelDataMap(String[] projArray){
		if(projArray == null){
			return null;
		}

		Map<String, List<String>> dataMap = new HashMap<>();
		for(String proj : projArray){
			if(StringUtils.isEmpty(proj)){
				continue;
			}

			List<String> datas = new ArrayList<>();
			datas.add(proj);
			dataMap.put(proj, datas);
		}

		return dataMap;
	}

	/**
	 * 将数据填充进对应的项目里
	 * @param projName
	 * @param data
	 * @param dataMap
	 */
	protected void setExcelCellData(String projName, String data, Map<String, List<String>> dataMap){
		if(dataMap == null){
			return;
		}

		List<String> datas = dataMap.get(projName);
		if(datas != null){
			datas.add(data);
			dataMap.put(projName, datas);
		}
	}
}
