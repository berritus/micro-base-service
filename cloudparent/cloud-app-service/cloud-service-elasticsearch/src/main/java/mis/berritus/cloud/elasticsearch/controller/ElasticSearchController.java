package mis.berritus.cloud.elasticsearch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2019/5/16
 */
@RestController
public class ElasticSearchController {

    @GetMapping("/")
    public String helloElasticSearch() {
        return "hello elasticsearch";
    }


}
