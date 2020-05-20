package mis.berritus.cloud.uaa.dao;

import com.berritus.mis.core.dao.MisDao;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUserRoleDTO;
import mis.berritus.cloud.bean.uaa.ext.SysUserRoleDTOExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleDao extends MisDao<SysUserRoleDTO, Long> {

    List<SysUserRoleDTOExt> listSysUserRole(SysUserRoleDTOExt record);
}