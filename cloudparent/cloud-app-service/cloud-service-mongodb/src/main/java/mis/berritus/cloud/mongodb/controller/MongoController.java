package mis.berritus.cloud.mongodb.controller;

import mis.berritus.cloud.app.bean.prod.SysProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/8/1
 */
@RestController
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/")
    public String index(){
        return "hello mongo";
    }

    @PostMapping("/mongo/saveSysProduct")
    public ModelAndView saveSysProduct(@RequestBody SysProduct sysProduct) {
        Map<String, Object> map = new HashMap<>();

        sysProduct.setCrtDate(new Date());
        mongoTemplate.save(sysProduct);

        map.put("rspCode", "0000");
        map.put("rspMsg", "成功");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @GetMapping("/mongo/getSysProduct")
    public ModelAndView getSysProduct(Integer pid) {
        Map<String, Object> map = new HashMap<>();

        Query query = new Query(Criteria.where("pid").is(pid));
        SysProduct sysProduct = mongoTemplate.findOne(query, SysProduct.class);
        map.put("rspCode", "0000");
        map.put("rspMsg", "成功");
        map.put("data", sysProduct);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @GetMapping("/mongo/deleteSysProduct")
    public ModelAndView deleteSysProduct(Integer pid) {
        Map<String, Object> map = new HashMap<>();

        Query query = new Query(Criteria.where("pid").is(pid));
        SysProduct sysProduct = mongoTemplate.findAndRemove(query, SysProduct.class);
        map.put("rspMsg", "成功");
        map.put("rspCode", "0000");
        map.put("data", sysProduct);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @PostMapping("/mongo/updateSysProduct")
    public ModelAndView updateSysProduct(@RequestBody SysProduct sysProduct) {
        Map<String, Object> map = new HashMap<>();

        Query query = new Query(Criteria.where("pid").is(sysProduct.getPid()));
        Update update = new Update();
        update.set("proName", sysProduct.getProName());
        mongoTemplate.upsert(query, update, SysProduct.class);
        map.put("rspMsg", "成功");
        map.put("rspCode", "0000");
        map.put("data", sysProduct);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
