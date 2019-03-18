package mis.berritus.cloud.logger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {

    @GetMapping("/")
    public String index(){
        return "this is logger system";
    }
}
