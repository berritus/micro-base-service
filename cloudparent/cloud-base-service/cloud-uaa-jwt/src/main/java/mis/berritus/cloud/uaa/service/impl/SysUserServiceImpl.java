package mis.berritus.cloud.uaa.service.impl;

import com.berritus.mis.core.component.service.MisCoreService;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.uaa.dao.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/3/15
 */
@Service
@Transactional
public class SysUserServiceImpl extends MisCoreService<SysUserMapper, SysUser, Integer> {
}
