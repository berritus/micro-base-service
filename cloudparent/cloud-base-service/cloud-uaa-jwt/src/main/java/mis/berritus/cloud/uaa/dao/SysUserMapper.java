package mis.berritus.cloud.uaa.dao;

import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.dao.MisDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends MisDao<SysUser, Integer> {
    SysUser selectByUserName(String userName);
}