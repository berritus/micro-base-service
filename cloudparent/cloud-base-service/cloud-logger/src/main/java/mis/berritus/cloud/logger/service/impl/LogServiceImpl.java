package mis.berritus.cloud.logger.service.impl;

import mis.berritus.cloud.bean.logger.SysRunLog;
import mis.berritus.cloud.logger.dao.SysRunLogMapper;
import mis.berritus.cloud.logger.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private SysRunLogMapper sysRunLogMapper;

    @Override
    public void insertSysRunLog(SysRunLog record) {
        sysRunLogMapper.insert(record);
    }
}
