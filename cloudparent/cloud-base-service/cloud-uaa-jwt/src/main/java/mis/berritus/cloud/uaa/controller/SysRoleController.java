package mis.berritus.cloud.uaa.controller;

import com.berritus.mis.core.common.utils.MisRandUtil;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.uaa.service.AuthService;
import mis.berritus.cloud.uaa.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/20
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private AuthService authService;
    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @PostMapping("/list")
    public PageInfo<SysRoleDTO> listSysRole(@RequestBody SysRoleDTO record) {
        return sysRoleService.listSysRole(record);
    }

    @PostMapping("/add")
    public SysRoleDTO insertSysRole(@RequestBody SysRoleDTO record){
        String uuid = MisRandUtil.uuid();
        record.setUuid(uuid);
        record.setCrtDate(new Date());
        record.setStateDate(new Date());
        record.setState((byte)0);
        sysRoleService.insert(record);
        return record;
    }

    @PostMapping("/del")
    public void delSysRole(@RequestParam("seqId") Long seqId){
        sysRoleService.deleteByPrimaryKey(seqId);
    }
}
