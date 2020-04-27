package mis.berritus.cloud.chat.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/4/27
 */
@RestController
public class ChatServerController {

    @RequestMapping("/")
    public String index(){
        return "hello chat server";
    }
}
