package mis.berritus.cloud.uaa.dao;

import com.berritus.mis.core.dao.MisDao;
import mis.berritus.cloud.bean.uaa.SysPermissionsDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionsDao extends MisDao<SysPermissionsDTO, Long> {

}