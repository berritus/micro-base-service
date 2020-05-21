package mis.berritus.cloud.uaa.service.base;

import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.uaa.dao.SysPermissionsDao;
import mis.berritus.cloud.uaa.dao.SysRoleDao;
import mis.berritus.cloud.uaa.dao.SysUserDao;
import mis.berritus.cloud.uaa.dao.SysUserPermissionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserPermissionsDao sysUserPermissionsDao;
    @Autowired
    private SysPermissionsDao sysPermissionsDao;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        String pwd = passwordEncoder.encode("123456");
//        User user = new User(userName, pwd,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        SysUserDTO user = sysUserDao.selectByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("没有该用户");
        }

        Set<String> userPermissions = new HashSet<>(10);
        List<SysRoleDTO> userRoles = sysRoleDao.getUserRoles(userName);
        if (!CollectionUtils.isEmpty(userRoles)) {
            for (SysRoleDTO sysRoleDTO : userRoles) {
                userPermissions.add(sysRoleDTO.getRoleCode());
            }
        }

        List<String> listParentCode = new ArrayList<>();

        List<String> userPermissionList = sysUserPermissionsDao.listUserPermissionCode(userName);
        if (!CollectionUtils.isEmpty(userPermissionList)) {
            for (String permission : userPermissionList) {
                userPermissions.add(permission);
                listParentCode.add(permission);
            }
        }

        listParentCode.add("22222");
        if (!CollectionUtils.isEmpty(listParentCode)) {
            List<String> listPermission = sysPermissionsDao.listPermissionCodeByParentCode(listParentCode);
            if (!CollectionUtils.isEmpty(listPermission)) {
                for (String permission : listPermission) {
                    userPermissions.add(permission);
                }
            }
        }

        return new MyUserDetails(user, userPermissions);
    }
}
