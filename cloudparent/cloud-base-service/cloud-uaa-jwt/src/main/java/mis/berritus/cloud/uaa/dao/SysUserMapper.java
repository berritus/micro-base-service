package mis.berritus.cloud.uaa.dao;

import com.berritus.mis.core.dao.MisDao;
import mis.berritus.cloud.bean.uaa.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends MisDao<SysUser, Integer> {
    SysUser selectByUserName(String userName);
}