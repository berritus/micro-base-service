package mis.berritus.cloud.service.prod.dao;


import mis.berritus.cloud.app.bean.prod.SysProduct;
import com.berritus.mis.core.dao.MisDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SysProductMapper extends MisDao<SysProduct, Integer> {

}