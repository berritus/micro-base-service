package mis.berritus.cloud.service.cust.task;

import com.berritus.mis.core.cache.lock.IRedisLock;
import com.berritus.mis.core.common.thread.MisExecutors;
import mis.berritus.cloud.app.bean.common.PeopleInfoDTO;
import mis.berritus.cloud.app.common.constant.CloudServiceCustConstant;
import mis.berritus.cloud.app.common.utils.RandomUtil;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.cust.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/28
 */
@Component
public class ServiceCustTask {
    private static final Logger logger = LoggerFactory.getLogger(ServiceCustTask.class);
    private static long lockTimeOut = 120000;

    @Autowired
    private DemoService demoService;
    @Autowired
    private IRedisLock redisLock;

    @Scheduled(fixedRate = 60000)
    public void batchRegisterCust(){
        boolean lock = false;
        String logId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        String value = (System.currentTimeMillis() + lockTimeOut) + "";

        logger.info("logId={},定时任务batchRegisterCust启动", logId);
        try{
            lock = redisLock.lock(CloudServiceCustConstant.TASK_BATCH_REGISTER_CUST, value);
            if (!lock) {
                logger.warn("logId={},定时任务batchRegisterCust正在运行中，本次不再运行", logId);
                return;
            }
            startTime = System.currentTimeMillis();

            int processorsNum = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = MisExecutors.newFixedThreadPool(processorsNum * 2 + 1);
            for (int i = 0; i < 500; i++) {
                RegisterCust registerCust = new RegisterCust();
                executor.submit(registerCust);
            }
            executor.shutdown();
            endTime = System.currentTimeMillis();
        } catch(Exception e) {
            logger.error("logId={}，定时任务batchRegisterCust出现异常：{}", logId, e);
        } finally{
            if (lock) {
                redisLock.unlock(CloudServiceCustConstant.TASK_BATCH_REGISTER_CUST, value);
                logger.info("logId={}，定时任务batchRegisterCust本次运行完毕！用时{}ms", logId, (endTime - startTime));
            }
        }
    }

    class RegisterCust implements Runnable {
        @Override
        public void run() {
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
