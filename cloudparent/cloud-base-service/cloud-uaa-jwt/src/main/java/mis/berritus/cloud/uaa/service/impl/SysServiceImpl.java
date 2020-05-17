package mis.berritus.cloud.uaa.service.impl;

import mis.berritus.cloud.bean.uaa.*;
import mis.berritus.cloud.common.utils.BPwdEncoderUtil;
import mis.berritus.cloud.uaa.dao.SysRoleDao;
import mis.berritus.cloud.uaa.dao.SysUserDao;
import mis.berritus.cloud.uaa.dao.SysUserRoleDao;
import mis.berritus.cloud.uaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    @Transactional
    public int insertSysRole(SysRoleDTO record) {
        String roleName = record.getRoleName().toUpperCase();

        SysRoleDTO role = sysRoleDao.getRoleByName(roleName);
        if(role != null){
            throw new RuntimeException("该角色已经存在");
        }

        record.setRoleName(roleName);
        record.setCrtDate(new Date());
        sysRoleDao.insert(record);

        return record.getSeqId();
    }

    @Override
    @Transactional
    public long insertSysUserRole(SysUserRoleDTO record) {
        record.setCrtDate(new Date());
        sysUserRoleDao.insert(record);
        return record.getSeqId();
    }

    @Override
    public SysUserDTO matchesUser(String username, String password) {
        SysUserDTO sysUser = sysUserDao.selectByUserName(username);
        if(sysUser == null){
            throw new RuntimeException("用户不存在");
        }

        boolean flag = BPwdEncoderUtil.matches(password, sysUser.getPassword());
        if(flag){
            return sysUser;
        }else{
            throw new RuntimeException("用户密码错误");
        }
    }
}
