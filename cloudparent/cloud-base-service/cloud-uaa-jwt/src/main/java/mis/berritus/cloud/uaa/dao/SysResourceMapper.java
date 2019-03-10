package mis.berritus.cloud.uaa.dao;


import mis.berritus.cloud.bean.uaa.SysResource;
import mis.berritus.cloud.dao.MisDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SysResourceMapper extends MisDao<SysResource, Integer> {
    SysResource selectByUrl(String url);
}