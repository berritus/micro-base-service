package mis.berritus.cloud.uaa.dao;

import com.berritus.mis.core.dao.MisDao;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao extends MisDao<SysRoleDTO, Integer> {
    List<SysRoleDTO> getUserRoles(String userName);

    List<SysRoleDTO> getResourceRoles(Integer resId);

    SysRoleDTO getRoleByName(String roleName);
}