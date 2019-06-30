package mis.berritus.cloud.service.cust.task;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.app.bean.common.PeopleInfoDTO;
import mis.berritus.cloud.app.common.utils.RandomUtil;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.cust.service.DemoService;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/28
 */
@Component
public class ServiceCustTask {
    private static final Logger logger = LoggerFactory.getLogger(ServiceCustTask.class);
    @Autowired
    private DemoService demoService;

    @Scheduled(fixedRate = 60000)
    public void batchRegisterCust(){
        boolean flag = false;

        try{
            long startTime = System.currentTimeMillis();

            int processorsNum = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(processorsNum * 2 + 1);
            for (int i = 0; i < 1; i++) {
                RegisterCust registerCust = new RegisterCust();
                executor.submit(registerCust);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("use times " + (endTime - startTime));
        } catch(Exception e) {
            logger.error("error " + e);
        } finally{

        }
    }

    class RegisterCust implements Runnable {
        @Override
        public void run() {
            //Random random = new Random();
            //int sex = random.nextInt(2) + 1;

            MisCustBase misCustBase = new MisCustBase();
            String accout = UUID.randomUUID().toString();
            misCustBase.setAccount(accout);
            misCustBase.setPassword("q123466");

            String value = RandomUtil.getEmail(7, 10);
            misCustBase.setEmail(value);
            PeopleInfoDTO peopleInfoDTO = RandomUtil.getChineseNameAndSex();
            misCustBase.setCertName(peopleInfoDTO.getName());
            misCustBase.setSex((byte) peopleInfoDTO.getSex().intValue());
            demoService.insertCustBase(misCustBase);
        }
    }
}
