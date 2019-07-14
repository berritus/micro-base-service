package mis.berritus.cloud.sys.web.feign.client.fallback;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.web.feign.client.SysServiceClient;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@Component
public class SysServiceClientFallback implements SysServiceClient {

    @Override
    public SystemParam insertSysParam(SystemParam systemParam) {
        return null;
    }

    @Override
    public PageInfo<SystemParam> listSysParams(SystemParam systemParam) {
        return null;
    }

    @Override
    public Integer delSysParam(String paramId) {
        return null;
    }

    @Override
    public SystemParam updateSystemParam(SystemParam systemParam) {
        return null;
    }
}
