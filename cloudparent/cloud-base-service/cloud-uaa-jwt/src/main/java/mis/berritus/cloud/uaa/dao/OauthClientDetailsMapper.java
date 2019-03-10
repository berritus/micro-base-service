package mis.berritus.cloud.uaa.dao;

import mis.berritus.cloud.bean.uaa.OauthClientDetails;
import mis.berritus.cloud.dao.MisDao;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsMapper extends MisDao<OauthClientDetails, String> {

}