package mis.berritus.cloud.sys.service.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.service.service.ISysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@RestController
@RequestMapping("/sys")
public class SysServiceController {
    private static final Logger logger = LoggerFactory.getLogger(SysServiceController.class);

    @Autowired
    private ISysService sysService;


    @GetMapping("/getSysParam")
    public SystemParam getSysParam(String paramCode) {
        return sysService.getSystemParam(paramCode);
    }

    @GetMapping("/delSysParam")
    public Integer delSysParam(String paramId) {
        return sysService.delSystemParam(paramId);
    }

    @PostMapping("/updateSystemParam")
    public SystemParam updateSystemParam(@RequestBody SystemParam systemParam) {
        return sysService.updateSystemParam(systemParam);
    }

    @PostMapping("/listSysParams")
    public PageInfo<SystemParam> listSysParams(@RequestBody SystemParam systemParam) {
        if (systemParam.getPageNum() == null) {
            systemParam.setPageNum(1);
        }

        if (systemParam.getPageSize()== null) {
            systemParam.setPageSize(10);
        }

        PageInfo<SystemParam> plist = sysService.listSystemParams(systemParam);
        return plist;
    }

    @RequestMapping("/insertSysParam")
    public SystemParam insertSysParam(@RequestBody SystemParam systemParam) {
        SystemParam systemParam1 = null;
        try {
             systemParam1 = sysService.insertSystemParam(systemParam);
        } catch (Exception e) {
            logger.error("新增系统参数失败，{}", e);
        }

        return systemParam1;
    }
}
