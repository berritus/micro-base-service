package mis.berritus.cloud.uaa.service.impl;

import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.uaa.dao.SysRoleDao;
import mis.berritus.cloud.uaa.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        String pwd = passwordEncoder.encode("123456");
//        User user = new User(userName, pwd,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        SysUserDTO user = sysUserDao.selectByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("没有该用户");
        }

        List<SysRoleDTO> userRoles = sysRoleDao.getUserRoles(userName);

        return new MyUserDetails(user, userRoles);
    }
}
