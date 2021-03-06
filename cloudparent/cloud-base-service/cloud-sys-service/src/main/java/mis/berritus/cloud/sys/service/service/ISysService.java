package mis.berritus.cloud.sys.service.service;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.sys.service.SystemParam;

import java.util.List;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
public interface ISysService {
    PageInfo<SystemParam> listSystemParams(SystemParam systemParam);

    SystemParam getSystemParam(String paramCode);

    SystemParam insertSystemParam(SystemParam systemParam);

    Integer delSystemParam(String paramId);

    SystemParam updateSystemParam(SystemParam systemParam);
}
