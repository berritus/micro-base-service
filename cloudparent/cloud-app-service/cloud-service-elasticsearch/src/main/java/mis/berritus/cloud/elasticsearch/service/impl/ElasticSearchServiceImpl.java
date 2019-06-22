package mis.berritus.cloud.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.berritus.mis.core.cache.redis.IRedisService;
import com.berritus.mis.core.component.utils.HttpUtil;
import mis.berritus.cloud.app.bean.elasticsearch.ElasticsearchRespone;
import mis.berritus.cloud.app.common.constant.SysConfigConstants;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.elasticsearch.feign.client.SysServiceClient;
import mis.berritus.cloud.elasticsearch.service.IElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {
    private String elasticSearchHost;

    @Autowired
    private SysServiceClient sysServiceClient;
    @Autowired
    private IRedisService redisService;

    @Override
    public String getAllIndexs() {
        String url = redisService.get(SysConfigConstants.ELASTIC_SEARCH_ALL_INDEXS_URL);

        if (StringUtils.isEmpty(url)) {
            SystemParam systemParam = sysServiceClient.getSystemParam(SysConfigConstants.ELASTIC_SEARCH_ALL_INDEXS_URL);

            if (systemParam != null) {
                url = systemParam.getParamValue();
                redisService.set(SysConfigConstants.ELASTIC_SEARCH_ALL_INDEXS_URL, url, 23*60*60*1000);
            }
        }

        String str = HttpUtil.get(url);
        return str;
    }

    @Override
    public ElasticsearchRespone createIndex(String indexName) {
        String url = redisService.get(SysConfigConstants.ELASTIC_SEARCH_HOST);

        if (StringUtils.isEmpty(url)) {
            SystemParam systemParam = sysServiceClient.getSystemParam(SysConfigConstants.ELASTIC_SEARCH_HOST);

            if (systemParam != null) {
                url = systemParam.getParamValue();
                redisService.set(SysConfigConstants.ELASTIC_SEARCH_HOST, url, 23*60*60*1000);
            }
        }


       String resultStr = HttpUtil.put(url + indexName);
        if (!StringUtils.isEmpty(resultStr)) {
            ElasticsearchRespone respone = JSON.parseObject(resultStr, ElasticsearchRespone.class);
            return respone;
        }

        return null;
    }

    @Override
    public ElasticsearchRespone deleteIndex(String indexName) {
        String url = redisService.get(SysConfigConstants.ELASTIC_SEARCH_HOST);

        if (StringUtils.isEmpty(url)) {
            SystemParam systemParam = sysServiceClient.getSystemParam(SysConfigConstants.ELASTIC_SEARCH_HOST);

            if (systemParam != null) {
                url = systemParam.getParamValue();
                redisService.set(SysConfigConstants.ELASTIC_SEARCH_HOST, url, 23*60*60*1000);
            }
        }


        String resultStr = HttpUtil.delete(url + indexName);
        if (!StringUtils.isEmpty(resultStr)) {
            ElasticsearchRespone respone = JSON.parseObject(resultStr, ElasticsearchRespone.class);
            return respone;
        }

        return null;
    }
}
