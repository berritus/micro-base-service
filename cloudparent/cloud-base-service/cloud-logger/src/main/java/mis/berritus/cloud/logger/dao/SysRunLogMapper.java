package mis.berritus.cloud.logger.dao;

import mis.berritus.cloud.bean.logger.SysRunLog;
import mis.berritus.cloud.dao.MisDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRunLogMapper extends MisDao<SysRunLog, Long> {

}