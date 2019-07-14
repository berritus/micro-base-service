package mis.berritus.cloud.sys.service.controller;

import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.service.service.ISysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/14
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private ISysService sysService;

    class ThreadTestInsert implements Runnable {
        private CountDownLatch countDownLatch;
        private SystemParam systemParam;

        public ThreadTestInsert(CountDownLatch countDownLatch, SystemParam systemParam) {
            this.countDownLatch = countDownLatch;
            this.systemParam = systemParam;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                sysService.insertSystemParam(systemParam);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/test1")
    public String test1() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // ExecutorService service = Executors.newFixedThreadPool(50);

        SystemParam systemParam = new SystemParam();
        systemParam.setParamCode("11111");
        systemParam.setParamName("ffsfsd");
        systemParam.setParamValue("2222");
        ThreadTestInsert threadTestInsert = new ThreadTestInsert(countDownLatch, systemParam);
        for (int i = 0; i < 50; i++) {
            new Thread(threadTestInsert).start();
        }

        countDownLatch.countDown();

        return "success";
    }
}
