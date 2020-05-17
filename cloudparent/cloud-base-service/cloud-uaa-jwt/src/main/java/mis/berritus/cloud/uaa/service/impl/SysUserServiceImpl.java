package mis.berritus.cloud.uaa.service.impl;

import com.berritus.mis.core.component.service.MisCoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.uaa.dao.SysUserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/3/15
 */
@Service
//@Transactional
public class SysUserServiceImpl extends MisCoreService<SysUserDao, SysUserDTO, Long> {

    public PageInfo<SysUserDTO> listSysUser(@RequestBody SysUserDTO record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<SysUserDTO> list = dao.listSysUser(record);
        PageInfo<SysUserDTO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    public SysUserDTO selectByUserName(String userName) {
        return dao.selectByUserName(userName);
    }

    public SysUserDTO delByUserName(String userName) {
        return dao.selectByUserName(userName);
    }
}
