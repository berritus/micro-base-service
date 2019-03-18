package mis.berritus.cloud.logger.service;

import mis.berritus.cloud.bean.logger.SysRunLog;

public interface ILogService {
    void insertSysRunLog(SysRunLog record);
}
