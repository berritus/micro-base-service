package mis.berritus.cloud.logger.dao;

import mis.berritus.cloud.bean.logger.SysRunLog;
import com.berritus.mis.core.dao.MisDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRunLogMapper extends MisDao<SysRunLog, Long> {

}