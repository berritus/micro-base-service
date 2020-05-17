package mis.berritus.cloud.common.web.feign.client.fallback;


import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.app.bean.uaa.MisJwt;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.bean.uaa.SysUserDTO;
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
        return null;
    }

    @Override
    public MisJwt getToken2(String authorization, String type, String username, String password) {
        return null;
    }

    @Override
    public String index(String str) {
        return null;
    }

    @Override
    public String addOauthClientDetails(OauthClientDetails record) {
        return null;
    }

    @Override
    public PageInfo<OauthClientDetails> getOauthClientDetails(OauthClientDetails record) {
        return null;
    }

    @Override
    public boolean delOauthClientDetails(String clientId) {
        return false;
    }

    @Override
    public PageInfo<SysUserDTO> listSysUser(SysUserDTO record) {
        return null;
    }

    @Override
    public SysUserDTO insertSysUser(SysUserDTO record) {
        return null;
    }

    @Override
    public SysUserDTO getSysUser(String userName) {
        return null;
    }

    @Override
    public void delSysUser(Long seqId) {
        logger.error("AuthServiceClientFallBack delSysUser");
    }
}
