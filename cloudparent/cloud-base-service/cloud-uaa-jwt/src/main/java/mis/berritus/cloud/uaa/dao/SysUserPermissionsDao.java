package mis.berritus.cloud.uaa.dao;

import mis.berritus.cloud.bean.uaa.SysUserPermissionsDTO;
import com.berritus.mis.core.dao.MisDao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserPermissionsDao extends MisDao<SysUserPermissionsDTO, Long> {
    List<String> listUserPermissionCode(String userName);
}