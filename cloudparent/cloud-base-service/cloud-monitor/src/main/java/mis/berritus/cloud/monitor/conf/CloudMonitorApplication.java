package mis.berritus.cloud.monitor.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient
@EnableTurbine//开启Turbine
public class CloudMonitorApplication {
    //要看仪表盘，先访问接口
    //http://localhost:8094/feign/qryCust等http://localhost:8092/ribbon/qryCust
    //再
    //http://localhost:8094/hystrix或者http://localhost:8092/hystrix
    //url处输入
    //http://localhost:8101/turbine.stream
    public static void main(String[] args){
        SpringApplication.run(CloudMonitorApplication.class);
    }
}
