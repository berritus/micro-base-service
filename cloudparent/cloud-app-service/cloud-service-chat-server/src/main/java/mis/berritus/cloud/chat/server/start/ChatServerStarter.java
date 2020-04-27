package mis.berritus.cloud.chat.server.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/4/27
 */
@Component
public class ChatServerStarter implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ChatServerStarter.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("************************Mis Chat Server Start ....************************");
        if(event.getApplicationContext() != this.applicationContext) {
            return;
        }

        MisChatServer misChatServer = MisChatServer.getInstance();
        misChatServer.init();
        misChatServer.start();
        logger.info("************************Mis Chat Server Start Over************************");
    }
}
