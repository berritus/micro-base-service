package mis.berritus.cloud.common.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/14
 */
@RestController
public class DemoController {

    // http://localhost:8113/hello
    @RequestMapping("/hello")
    public String hello(Model model) {
        return "hello web";
    }
}
