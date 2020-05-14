package mis.berritus.cloud.sys.web.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/14
 */
@Configuration
public class MisWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main/ooa/list").setViewName("/main/ooa/list");
        registry.addViewController("/main/ooa/business").setViewName("/main/ooa/business");

    }
}
