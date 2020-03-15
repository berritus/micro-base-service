package mis.berritus.cloud.sys.service.service.impl;

import com.berritus.mis.core.cache.lock.IRedisLock;
import com.berritus.mis.core.common.utils.MisRandUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.sys.service.dao.SystemParamMapper;
import mis.berritus.cloud.sys.service.service.ISysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private static long lockTimeOut = 120000;

    @Autowired
    private SystemParamMapper systemParamMapper;
    @Autowired
    private IRedisLock redisLock;

    @Override
    public PageInfo<SystemParam> listSystemParams(SystemParam systemParam) {
        PageHelper.startPage(systemParam.getPageNum(), systemParam.getPageSize());
        List<SystemParam> list = systemParamMapper.listSystemParams(systemParam);
        PageInfo<SystemParam> pageInfo = new PageInfo<>(list);
        return pageInfo;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public SystemParam insertSystemParam(SystemParam systemParam){
        String infoMsg = "";

        String key = systemParam.getParamCode();
        String value = (System.currentTimeMillis() + lockTimeOut) + "";

        boolean lock = redisLock.lock(key, value);
        if (!lock) {
            infoMsg = "存在并发重复添加，paramCode：" + systemParam.getParamCode();
            logger.warn(infoMsg);
            redisLock.unlock(key, value);
            throw new RuntimeException(infoMsg);
        }

        SystemParam systemParam1 = getSystemParam(systemParam.getParamCode());
        if (systemParam1 != null) {
            infoMsg = "该系统编码已经存在，paramCode：" + systemParam.getParamCode();
            logger.warn(infoMsg);
            redisLock.unlock(key, value);
            throw new RuntimeException(infoMsg);
        }

        String uuid = MisRandUtil.uuid();
        systemParam.setUuid(uuid);
        systemParam.setCrtDate(new Date());
        systemParam.setState((byte)0);
        systemParam.setStateDate(new Date());
        systemParamMapper.insert(systemParam);

        redisLock.unlock(key, value);
        return systemParam;
    }

    @Override
    @Transactional
    public Integer delSystemParam(String paramId) {
        return systemParamMapper.deleteByPrimaryKey(paramId);
    }

    @Override
    @Transactional
    public SystemParam updateSystemParam(SystemParam systemParam) {

        systemParam.setStateDate(new Date());
        systemParamMapper.updateByPrimaryKeySelective(systemParam);
        return systemParam;
    }
}
