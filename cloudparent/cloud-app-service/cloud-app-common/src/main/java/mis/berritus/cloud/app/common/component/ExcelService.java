package mis.berritus.cloud.app.common.component;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ExcelService {
    public void exportList(String fileName, List<String> header, Map<String, List<String>> dataMap,
                           OutputStream outputStream)
            throws InvocationTargetException, IllegalAccessException;
}
