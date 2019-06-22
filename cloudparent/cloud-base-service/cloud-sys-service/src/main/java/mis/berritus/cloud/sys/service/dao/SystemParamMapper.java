package mis.berritus.cloud.sys.service.dao;

import com.berritus.mis.core.dao.MisDao;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemParamMapper extends MisDao<SystemParam, String> {
    List<SystemParam> listSystemParams(SystemParam systemParam);
}