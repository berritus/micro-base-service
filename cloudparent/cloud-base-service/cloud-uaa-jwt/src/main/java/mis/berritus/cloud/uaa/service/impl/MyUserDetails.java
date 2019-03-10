package mis.berritus.cloud.uaa.service.impl;

import mis.berritus.cloud.bean.uaa.SysRole;
import mis.berritus.cloud.bean.uaa.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyUserDetails implements UserDetails {
    private String userName;
    private String password;
    private List<SysRole> roles;

    public MyUserDetails(){

    }

    public MyUserDetails(SysUser user){
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    public MyUserDetails(SysUser user, List<SysRole> roles){
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(SysRole role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
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

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
