package mis.berritus.cloud.uaa.service.impl;

import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.uaa.dao.SysRoleMapper;
import mis.berritus.cloud.uaa.dao.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        String pwd = passwordEncoder.encode("123456");
//        User user = new User(userName, pwd,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        SysUser user = sysUserMapper.selectByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("没有该用户");
        }

        List<SysRole> userRoles = sysRoleMapper.getUserRoles(userName);

        return new MyUserDetails(user, userRoles);
    }
}
