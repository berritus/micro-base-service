package mis.berritus.cloud.common.web.feign.client.fallback;


import mis.berritus.cloud.app.bean.uaa.MisJwt;
import mis.berritus.cloud.bean.uaa.SysUser;
import mis.berritus.cloud.common.web.feign.client.AuthServiceClient;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceClientFallBack implements AuthServiceClient {

    @Override
    public SysUser matchesUser(String userName, String password){
        return null;
    }

    @Override
    public MisJwt getToken2(String authorization, String type, String username, String password) {
        return null;
    }


}
