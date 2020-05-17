package mis.berritus.cloud.uaa.service;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.uaa.OauthClientDetails;

import java.util.List;

public interface AuthService {
    void insertOauthClientDetails(OauthClientDetails record);

    PageInfo<OauthClientDetails> listOauthClientDetails(OauthClientDetails record);

    boolean delOauthClientDetails(String clientId);

    String encryptionPassword(String password);
}
