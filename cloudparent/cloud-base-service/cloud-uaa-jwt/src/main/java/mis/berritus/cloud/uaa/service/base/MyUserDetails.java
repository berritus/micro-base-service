package mis.berritus.cloud.uaa.service.base;

import mis.berritus.cloud.bean.uaa.SysUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class MyUserDetails implements UserDetails {
    private String userName;
    private String password;
    private Set<String> userPermissions;

    public MyUserDetails(){

    }

    public MyUserDetails(SysUserDTO user){
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    public MyUserDetails(SysUserDTO user, Set<String> userPermissions){
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.userPermissions = userPermissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String permission : userPermissions){
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        //判断账号是否已经过期，默认没有过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //判断账号是否被锁定，默认没有锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //判断信用凭证是否过期，默认没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        //判断账号是否可用，默认可用
        return true;
    }

    public Set<String> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Set<String> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
