package mis.berritus.cloud.elasticsearch.controller;

import mis.berritus.cloud.app.bean.elasticsearch.ElasticsearchRespone;
import mis.berritus.cloud.app.bean.elasticsearch.MisCustBaseExt;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.elasticsearch.service.IElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2019/5/16
 */
@RestController
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    private IElasticSearchService elasticSearchService;

    @GetMapping("/")
    public String helloElasticSearch() {
        return "hello elasticsearch";
    }

    @GetMapping("/getIndexs")
    public String getAllIndexs() {
        return elasticSearchService.getAllIndexs();
    }

    @GetMapping("/createIndex")
    public ElasticsearchRespone createIndex(String indexName) {
        return elasticSearchService.createIndex(indexName);
    }

    @GetMapping("/deleteIndex")
    public ElasticsearchRespone deleteIndex(String indexName) {
        return elasticSearchService.deleteIndex(indexName);
    }

    @PostMapping("/cust/insertMisCustBase")
    public MisCustBaseExt insertMisCustBase(@RequestBody MisCustBaseExt misCustBaseExt) {
        return elasticSearchService.insertMisCustBase(misCustBaseExt);
    }
}
