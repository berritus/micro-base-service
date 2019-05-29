package mis.berritus.cloud.app.bean.excel;

import mis.berritus.cloud.bean.base.MisBean;

import java.util.List;
import java.util.Map;

/**
 * @Program: cloud-parent
 * @Copyright
 * @Author: Qin Guihe
 * @Create: 2019-05-27 10:50
 * @Description:
 */
public class ExportDataBean extends MisBean {
	private static final long serialVersionUID = 2352430064928752746L;
	private String sheetName;
	private List<String> headers;
	private Map<String, java.util.List<String>> dataMap;

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public Map<String, List<String>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, List<String>> dataMap) {
		this.dataMap = dataMap;
	}
}
