package mis.berritus.cloud.uaa.service.impl;

import com.berritus.mis.core.component.service.MisCoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.uaa.dao.SysRoleDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/5/20
 */
@Service
public class SysRoleServiceImpl extends MisCoreService<SysRoleDao, SysRoleDTO, Long> {

    public PageInfo<SysRoleDTO> listSysRole(@RequestBody SysRoleDTO record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<SysRoleDTO> list = dao.listSysRole(record);
        PageInfo<SysRoleDTO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
