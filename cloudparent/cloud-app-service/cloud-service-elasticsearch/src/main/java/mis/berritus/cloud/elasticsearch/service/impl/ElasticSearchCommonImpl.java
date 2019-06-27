package mis.berritus.cloud.elasticsearch.service.impl;

import com.berritus.mis.core.cache.redis.IRedisService;
import mis.berritus.cloud.app.common.constant.SysConfigConstants;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.elasticsearch.feign.client.SysServiceClient;
import mis.berritus.cloud.elasticsearch.service.IElasticSearchCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/28
 */
@Component
public class ElasticSearchCommonImpl implements IElasticSearchCommon {
    @Autowired
    private SysServiceClient sysServiceClient;
    @Autowired
    private IRedisService redisService;

    @Override
    public String getParamValue(String paramCode) {
        String paramValue = redisService.get(paramCode);

        synchronized (this) {
            paramValue = redisService.get(paramCode);
            if (StringUtils.isEmpty(paramValue)) {
                SystemParam systemParam = sysServiceClient.getSystemParam(paramCode);

                if (systemParam != null) {
                    paramValue = systemParam.getParamValue();
                    redisService.set(paramCode, paramValue, 23 * 60 * 60 * 1000);
                }
            }
        }

        return paramValue;
    }
}
