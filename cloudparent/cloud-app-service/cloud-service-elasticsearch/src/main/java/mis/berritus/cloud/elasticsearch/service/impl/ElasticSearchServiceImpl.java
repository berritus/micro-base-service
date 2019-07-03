package mis.berritus.cloud.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.berritus.mis.core.cache.redis.IRedisService;
import com.berritus.mis.core.component.utils.HttpUtil;
import mis.berritus.cloud.app.bean.elasticsearch.ElasticsearchRespone;
import mis.berritus.cloud.app.bean.elasticsearch.MisCustBaseExt;
import mis.berritus.cloud.app.bean.elasticsearch.QueryDTO;
import mis.berritus.cloud.app.bean.elasticsearch.SearchDTO;
import mis.berritus.cloud.app.common.constant.SysConfigConstants;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.elasticsearch.feign.client.ElasticServerClient;
import mis.berritus.cloud.elasticsearch.feign.client.SysServiceClient;
import mis.berritus.cloud.elasticsearch.service.IElasticSearchCommon;
import mis.berritus.cloud.elasticsearch.service.IElasticSearchService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders.*;
/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);
    private static TransportClient client;

    @Autowired
    private SysServiceClient sysServiceClient;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IElasticSearchCommon elasticSearchCommon;
    @Autowired
    private ElasticServerClient elasticServerClient;


    static {
        try {
            // on startup
//            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                    .addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300))
//                    .addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));

            // on shutdown
            // client.close();
            // Note that you have to set the cluster name if you use one different than "elasticsearch":
//            Settings settings = Settings.builder()
//                    .put("cluster.name", "myClusterName").build();
//            TransportClient client = new PreBuiltTransportClient(settings);
            //Add transport addresses and do something with the client...

            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (Exception e) {
            logger.error("ElasticSearch Clinet 异常：{}", e);
        }
    }

    @Override
    public String getAllIndexs() {
        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_ALL_INDEXS_URL);

        String str = HttpUtil.get(url);
        return str;
    }

    @Override
    public Integer createIndex(String indexName) {
//        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_HOST);
//
//        String resultStr = HttpUtil.put(url + indexName);
//        if (!StringUtils.isEmpty(resultStr)) {
//            ElasticsearchRespone respone = JSON.parseObject(resultStr, ElasticsearchRespone.class);
//            return respone;
//        }
        try {
            client.admin().indices().prepareCreate(indexName).get();
            //client.close();
            return 0;
        } catch (Exception e) {
            logger.error("新增index[{}]失败", indexName, e);
        }

        return 1;
    }

    @Override
    public Integer deleteIndex(String indexName) {
//        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_HOST);
//
//        String resultStr = HttpUtil.delete(url + indexName);
//        if (!StringUtils.isEmpty(resultStr)) {
//            ElasticsearchRespone respone = JSON.parseObject(resultStr, ElasticsearchRespone.class);
//            return respone;
//        }
        try {
            client.admin().indices().prepareDelete(indexName).get();
            // client.close();
            return 0;
        } catch (Exception e) {
            logger.error("删除index[{}]失败", indexName, e);
        }

        return 1;
    }

    @Override
    public MisCustBaseExt insertMisCustBase(MisCustBaseExt misCustBaseExt) {
        if (misCustBaseExt == null) {
            return null;
        }

        MisCustBase misCustBase = new MisCustBase();

        BeanUtils.copyProperties(misCustBaseExt, misCustBase);

        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_HOST);
        url += misCustBaseExt.getEsIndex() + "/" + misCustBaseExt.getEsType() + "/" + misCustBaseExt.getCustId();

        String json = JSON.toJSONString(misCustBase);
        Map<String, Object> params = (Map<String, Object>)JSON.parse(json);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        String result = HttpUtil.put(url, headers, params);

        ElasticsearchRespone respone = null;
        if (!StringUtils.isEmpty(result)) {
            respone = JSON.parseObject(result, ElasticsearchRespone.class);

            if (respone != null && respone.get_shards() != null && respone.get_shards().getSuccessful() == 1) {
                return misCustBaseExt;
            }
        }

        return null;
    }

    @Override
    public List<ElasticsearchRespone> listMisCustBases(MisCustBaseExt misCustBaseExt) {

        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_HOST);
        url += misCustBaseExt.getEsIndex() + "/" + misCustBaseExt.getEsType() + "/_search";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");

        Map<String, Object> query = new HashMap<>();
//
        QueryDTO queryDTO = new QueryDTO();
        MisCustBase misCustBase = new MisCustBase();
        misCustBase.setCertName("边新胜");
        //Map<String, String> match = new HashMap<>();
        //query.put("match", JSON.toJSONString(misCustBase));
        queryDTO.setMatch(misCustBase);

        Map<String, Object> params = new HashMap<>();
        params.put("query", queryDTO);
        params.put("size", 10);
//
        String result = HttpUtil.post(url, headers, params);

        return null;
    }

    @Override
    public Integer createIndexByJson(String json) {
        try {
           String uuid = UUID.randomUUID().toString();
            String json2 = "{" +
                    "\"user\":\"kimchy\"," +
                    "\"postDate\":\"2013-01-30\"," +
                    "\"message\":\"trying out Elasticsearch\"" +
                    "}";
            IndexResponse indexResponse = client.prepareIndex("userInfo", "baseInfo", uuid).setSource(json2).execute().actionGet();
            return 0;
        } catch (Exception e) {
            logger.error("新增index[{}]失败", "userInfo", e);
        }

        return 1;
    }


}
