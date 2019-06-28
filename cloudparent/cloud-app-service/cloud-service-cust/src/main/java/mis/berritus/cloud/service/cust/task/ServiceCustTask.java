package mis.berritus.cloud.service.cust.task;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.cust.service.DemoService;
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

    @Autowired
    private DemoService demoService;

    @Scheduled(fixedRate = 60000)
    public void batchRegisterCust(){
        boolean flag = false;

        try{
            long startTime = System.currentTimeMillis();

            int processorsNum = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(processorsNum * 2 + 1);
            for (int i = 0; i < 1000; i++) {
                RegisterCust registerCust = new RegisterCust();
                executor.submit(registerCust);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("use times " + (endTime - startTime));
        } catch(Exception e) {
            System.out.println("error " + e);
        } finally{

        }
    }

    class RegisterCust implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            int sex = random.nextInt(2) + 1;

            MisCustBase misCustBase = new MisCustBase();
            String accout = UUID.randomUUID().toString();
            misCustBase.setAccount(accout);
            misCustBase.setPassword("q123466");
            misCustBase.setSex((byte)sex);
            demoService.insertCustBase(misCustBase);
        }
    }
}
