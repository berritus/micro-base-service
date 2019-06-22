package mis.berritus.cloud.sys.service.controller;

import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.service.service.ISysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@RestController
@RequestMapping("/sys")
public class SysServiceController {

    @Autowired
    private ISysService sysService;

    @GetMapping("/getSysParam")
    public SystemParam getSysParam(String paramCode) {
        return sysService.getSystemParam(paramCode);
    }

    @RequestMapping("/insertSysParam")
    public SystemParam insertSysParam(@RequestBody SystemParam systemParam) {
        return sysService.insertSystemParam(systemParam);
    }
}
