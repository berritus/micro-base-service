package mis.berritus.cloud.uaa.dao;

import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.dao.MisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends MisDao<SysRole, Integer> {
    List<SysRole> getUserRoles(String userName);

    List<SysRole> getResourceRoles(Integer resId);

    SysRole getRoleByName(String roleName);
}