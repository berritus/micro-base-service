package mis.berritus.cloud.sys.web.feign.client;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.web.feign.client.conf.DemoFeignConfig;
import mis.berritus.cloud.sys.web.feign.client.fallback.SysServiceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/14
 */
@FeignClient(value = "cloud-sys-service", fallback = SysServiceClientFallback.class, configuration = DemoFeignConfig.class)
public interface SysServiceClient {
    @RequestMapping("/sys/insertSysParam")
    SystemParam insertSysParam(@RequestBody SystemParam systemParam);

    @PostMapping("/sys/listSysParams")
    PageInfo<SystemParam> listSysParams(@RequestBody SystemParam systemParam);

    @GetMapping("/sys/delSysParam")
    Integer delSysParam(@RequestParam("paramId") String paramId);

    @PostMapping("/sys/updateSystemParam")
    SystemParam updateSystemParam(@RequestBody SystemParam systemParam);

}
