package mis.berritus.cloud.test;

import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.service.prod.conf.ProductServerJwtApplication;
import mis.berritus.cloud.service.prod.feign.client.AuthServiceClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServerJwtApplication.class)
public class DemoTest {
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private AuthServiceClient authServiceClient;

    private class ThreadTest implements Runnable{

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SysUser sysUser = authServiceClient.matchesUser("admin", "123456");

            if(sysUser != null){
                System.out.println(Thread.currentThread().getName() + " success");
            }else{
                System.out.println(Thread.currentThread().getName() + " error");
            }
        }
    }

    @Test
    public void test1(){
        ThreadTest threadTest = new ThreadTest();

        for(int i = 0; i < 1; i++){
            new Thread(threadTest).start();
        }

        countDownLatch.countDown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
