package mis.berritus.cloud.uaa.dao;


import com.berritus.mis.core.dao.MisDao;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface SysUserDao extends MisDao<SysUserDTO, Long> {
    SysUserDTO selectByUserName(String userName);
    List<SysUserDTO> listSysUser(SysUserDTO record);
}