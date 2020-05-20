package mis.berritus.cloud.uaa.service.impl;

import com.berritus.mis.core.component.service.MisCoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUserRoleDTO;
import mis.berritus.cloud.bean.uaa.ext.SysUserRoleDTOExt;
import mis.berritus.cloud.uaa.dao.SysRoleDao;
import mis.berritus.cloud.uaa.dao.SysUserRoleDao;
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
public class SysUserRoleServiceImpl extends MisCoreService<SysUserRoleDao, SysUserRoleDTO, Long> {

    public PageInfo<SysUserRoleDTOExt> listSysUserRole(@RequestBody SysUserRoleDTOExt record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<SysUserRoleDTOExt> list = dao.listSysUserRole(record);
        PageInfo<SysUserRoleDTOExt> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
