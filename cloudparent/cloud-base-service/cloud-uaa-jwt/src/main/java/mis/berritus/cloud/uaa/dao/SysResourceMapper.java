package mis.berritus.cloud.uaa.dao;


import mis.berritus.cloud.bean.uaa.SysResource;
import com.berritus.mis.core.dao.MisDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SysResourceMapper extends MisDao<SysResource, Integer> {
    SysResource selectByUrl(String url);
}