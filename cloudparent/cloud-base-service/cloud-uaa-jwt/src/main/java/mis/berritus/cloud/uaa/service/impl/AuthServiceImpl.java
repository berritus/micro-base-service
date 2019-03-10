package mis.berritus.cloud.uaa.service.impl;


import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.uaa.dao.OauthClientDetailsMapper;
import mis.berritus.cloud.uaa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insertOauthClientDetails(OauthClientDetails record) {
        OauthClientDetails orgClient = oauthClientDetailsMapper.selectByPrimaryKey(record.getClientId());
        if(orgClient != null){
            throw new RuntimeException("client_id is exists");
        }

        record.setClientSecret(passwordEncoder.encode(record.getClientSecret()));
        oauthClientDetailsMapper.insert(record);
    }
}
