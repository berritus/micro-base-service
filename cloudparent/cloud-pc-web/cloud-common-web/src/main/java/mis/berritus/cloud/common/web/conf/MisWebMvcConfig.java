package mis.berritus.cloud.common.web.conf;

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
        registry.addViewController("/main/ooa/plus").setViewName("/main/ooa/plus");
        registry.addViewController("/main/ooa/index2").setViewName("/main/ooa/index2");

        registry.addViewController("/main/auth/oauth_client").setViewName("/main/auth/oauth_client");
        registry.addViewController("/main/auth/plus").setViewName("/main/auth/plus");

        registry.addViewController("/main/sys/param/list").setViewName("/main/sys/param/list");
        registry.addViewController("/main/sys/param/add").setViewName("/main/sys/param/add");

        registry.addViewController("/main/sys/user/list").setViewName("/main/sys/user/list");
        registry.addViewController("/main/sys/user/plus").setViewName("/main/sys/user/plus");

        registry.addViewController("/main/sys/role/list").setViewName("/main/sys/role/list");
        registry.addViewController("/main/sys/role/plus").setViewName("/main/sys/role/plus");

        registry.addViewController("/main/sys/userRole/list").setViewName("/main/sys/userRole/list");
        registry.addViewController("/main/sys/userRole/plus").setViewName("/main/sys/userRole/plus");
    }
}
