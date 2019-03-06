package mis.berritus.cloud.uaa.service.impl;

import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.bean.uaa.SysUserRole;
import mis.berritus.cloud.uaa.dao.SysRoleMapper;
import mis.berritus.cloud.uaa.dao.SysUserRoleMapper;
import mis.berritus.cloud.uaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class SysServiceImpl implements SysService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
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
    public int insertSysUserRole(SysUserRole record) {
        record.setCrtDate(new Date());
        sysUserRoleMapper.insert(record);
        return record.getId();
    }

}
