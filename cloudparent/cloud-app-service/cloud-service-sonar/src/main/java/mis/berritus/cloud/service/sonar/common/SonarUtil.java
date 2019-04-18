package mis.berritus.cloud.service.sonar.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Program: cloud-parent
 * @Copyright
 * @Author: Qin Guihe
 * @Create: 2019-04-18 16:13
 * @Description:
 */
public class SonarUtil {
	private static Logger logger = LoggerFactory.getLogger(SonarUtil.class);

	public static void setResponseBaseInfo(HttpServletResponse response, String fileName){
		response.setContentType("application/octet-stream;charset=utf-8");
		try {
			response.setHeader("Content-disposition", "attachment;filename=" +
					new String(fileName.getBytes("utf-8"),"ISO-8859-1" ) + ".xls");
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
