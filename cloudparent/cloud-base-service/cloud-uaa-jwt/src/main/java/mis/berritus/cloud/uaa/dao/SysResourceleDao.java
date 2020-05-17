package mis.berritus.cloud.uaa.dao;

import com.berritus.mis.core.dao.MisDao;
import mis.berritus.cloud.bean.uaa.SysResource;
import mis.berritus.cloud.bean.uaa.SysResourceleDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface SysResourceleDao extends MisDao<SysResourceleDTO, Integer> {
    SysResource selectByUrl(String url);
}