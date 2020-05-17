package mis.berritus.cloud.uaa.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.uaa.dao.OauthClientDetailsMapper;
import mis.berritus.cloud.uaa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void insertOauthClientDetails(OauthClientDetails record) {
        OauthClientDetails orgClient = oauthClientDetailsMapper.selectByPrimaryKey(record.getClientId());
        if(orgClient != null){
            throw new RuntimeException("client_id is exists");
        }

        if (record.getAccessTokenValidity() == null) {
            record.setAccessTokenValidity(3600);
        }

        if (record.getRefreshTokenValidity() == null) {
            record.setRefreshTokenValidity(3600);
        }

        record.setAccessTokenValidity(record.getAccessTokenValidity() * 3600);
        record.setRefreshTokenValidity(record.getRefreshTokenValidity() * 3600);

        record.setClientSecret(passwordEncoder.encode(record.getClientSecret()));
        record.setAuthorizedGrantTypes("client_credentials,refresh_token,password");
        record.setAutoapprove("true");
        oauthClientDetailsMapper.insert(record);
    }

    @Override
    public PageInfo<OauthClientDetails> listOauthClientDetails(OauthClientDetails record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());

        List<OauthClientDetails> list = oauthClientDetailsMapper.listOauthClientDetails(record);
        if (!CollectionUtils.isEmpty(list)) {
            for (OauthClientDetails bean : list) {
                bean.setClientSecret("*******");
            }
        }
        PageInfo<OauthClientDetails> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean delOauthClientDetails(String clientId) {
        oauthClientDetailsMapper.deleteByPrimaryKey(clientId);
        return true;
    }

    @Override
    public String encryptionPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
