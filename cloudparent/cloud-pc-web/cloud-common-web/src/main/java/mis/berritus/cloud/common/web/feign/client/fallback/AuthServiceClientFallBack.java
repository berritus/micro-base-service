package mis.berritus.cloud.common.web.feign.client.fallback;


import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.app.bean.uaa.MisJwt;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.bean.uaa.SysRoleDTO;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
import mis.berritus.cloud.bean.uaa.ext.SysUserRoleDTOExt;
import mis.berritus.cloud.common.web.controller.OauthController;
import mis.berritus.cloud.common.web.feign.client.AuthServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthServiceClientFallBack implements AuthServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceClientFallBack.class);

    @Override
    public SysUserDTO matchesUser(String userName, String password){
        logger.error("AuthServiceClientFallBack matchesUser");
        return null;
    }

    @Override
    public MisJwt getToken2(String authorization, String type, String username, String password) {
        logger.error("AuthServiceClientFallBack getToken2");
        return null;
    }

    @Override
    public String index(String str) {
        logger.error("AuthServiceClientFallBack index");
        return null;
    }

    @Override
    public String addOauthClientDetails(OauthClientDetails record) {
        logger.error("AuthServiceClientFallBack addOauthClientDetails");
        return null;
    }

    @Override
    public PageInfo<OauthClientDetails> getOauthClientDetails(OauthClientDetails record) {
        logger.error("AuthServiceClientFallBack getOauthClientDetails");
        return null;
    }

    @Override
    public boolean delOauthClientDetails(String clientId) {
        logger.error("AuthServiceClientFallBack delOauthClientDetails");
        return false;
    }

    @Override
    public PageInfo<SysUserDTO> listSysUser(SysUserDTO record) {
        logger.error("AuthServiceClientFallBack listSysUser");
        return null;
    }

    @Override
    public SysUserDTO insertSysUser(SysUserDTO record) {
        logger.error("AuthServiceClientFallBack insertSysUser");
        return null;
    }

    @Override
    public SysUserDTO getSysUser(String userName) {
        logger.error("AuthServiceClientFallBack getSysUser");
        return null;
    }

    @Override
    public void delSysUser(Long seqId) {
        logger.error("AuthServiceClientFallBack delSysUser");
    }

    @Override
    public PageInfo<SysRoleDTO> listSysRole(SysRoleDTO record) {
        logger.error("AuthServiceClientFallBack listSysRole");
        return null;
    }

    @Override
    public SysRoleDTO insertSysRole(SysRoleDTO record) {
        logger.error("AuthServiceClientFallBack insertSysRole");
        return null;
    }

    @Override
    public void delSysRole(Long seqId) {
        logger.error("AuthServiceClientFallBack delSysRole");
    }

    @Override
    public PageInfo<SysUserRoleDTOExt> listSysUserRole(SysUserRoleDTOExt record) {
        logger.error("AuthServiceClientFallBack listSysUserRole");
        return null;
    }
}
