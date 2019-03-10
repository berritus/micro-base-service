package mis.berritus.cloud.uaa.service.impl;

import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserRole;
import mis.berritus.cloud.common.utils.BPwdEncoderUtil;
import mis.berritus.cloud.uaa.dao.SysRoleMapper;
import mis.berritus.cloud.uaa.dao.SysUserMapper;
import mis.berritus.cloud.uaa.dao.SysUserRoleMapper;
import mis.berritus.cloud.uaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public int insertSysRole(SysRole record) {
        String roleName = record.getRoleName().toUpperCase();

        SysRole role = sysRoleMapper.getRoleByName(roleName);
        if(role != null){
            throw new RuntimeException("该角色已经存在");
        }

        record.setRoleName(roleName);
        record.setCrtDate(new Date());
        sysRoleMapper.insert(record);

        return record.getRid();
    }

    @Override
    @Transactional
    public int insertSysUserRole(SysUserRole record) {
        record.setCrtDate(new Date());
        sysUserRoleMapper.insert(record);
        return record.getId();
    }

    @Override
    public SysUser matchesUser(String username, String password) {
        SysUser sysUser = sysUserMapper.selectByUserName(username);
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
