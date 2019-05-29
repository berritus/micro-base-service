package mis.berritus.cloud.app.common.component;

import mis.berritus.cloud.app.bean.excel.ExportDataBean;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ExcelService {
    /**
     * @Description:
     * @Author: Qin Guihe
     * @Create: 2019/5/27
     * @return:
     */
    void exportList(String sheetName, List<String> header, Map<String, List<String>> dataMap,
                           OutputStream outputStream)
            throws InvocationTargetException, IllegalAccessException;

    /**
     * @Description:
     * @Author: Qin Guihe
     * @Create: 2019/5/27
     * @return:
     */
    void exportListForSheet(List<ExportDataBean> listData, OutputStream outputStream)
            throws InvocationTargetException, IllegalAccessException;
}
