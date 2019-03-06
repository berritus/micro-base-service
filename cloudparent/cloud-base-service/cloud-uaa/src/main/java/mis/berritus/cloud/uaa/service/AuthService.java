package mis.berritus.cloud.uaa.service;

import mis.berritus.cloud.bean.uaa.OauthClientDetails;

public interface AuthService {
    void insertOauthClientDetails(OauthClientDetails record);
}
