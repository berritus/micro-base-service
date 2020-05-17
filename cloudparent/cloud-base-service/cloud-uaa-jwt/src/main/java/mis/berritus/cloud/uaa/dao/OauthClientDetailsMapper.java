package mis.berritus.cloud.uaa.dao;

import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import com.berritus.mis.core.dao.MisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OauthClientDetailsMapper extends MisDao<OauthClientDetails, String> {

    List<OauthClientDetails> listOauthClientDetails(OauthClientDetails record);
}