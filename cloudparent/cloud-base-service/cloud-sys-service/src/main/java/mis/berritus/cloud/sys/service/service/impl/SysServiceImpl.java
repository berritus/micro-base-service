package mis.berritus.cloud.sys.service.service.impl;

import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.service.dao.SystemParamMapper;
import mis.berritus.cloud.sys.service.service.ISysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@Service
public class SysServiceImpl implements ISysService {
    private Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);

    @Autowired
    private SystemParamMapper systemParamMapper;

    @Override
    public List<SystemParam> listSystemParams(SystemParam systemParam) {

        return systemParamMapper.listSystemParams(systemParam);
    }

    @Override
    public SystemParam getSystemParam(String paramCode) {

        SystemParam systemParam = new SystemParam();
        systemParam.setParamCode(paramCode);

        List<SystemParam> list = systemParamMapper.listSystemParams(systemParam);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public SystemParam insertSystemParam(SystemParam systemParam) {
        String uuid = UUID.randomUUID().toString();

        systemParam.setUuid(uuid);
        systemParam.setCrtDate(new Date());
        systemParam.setState((byte)0);
        systemParam.setStateDate(new Date());
        systemParamMapper.insert(systemParam);

        return systemParam;
    }
}
