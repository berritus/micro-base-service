package mis.berritus.cloud.uaa.dao;

import com.berritus.mis.core.dao.MisDao;
import mis.berritus.cloud.bean.uaa.SysPermissionsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionsDao extends MisDao<SysPermissionsDTO, Long> {

    List<String> listPermissionCodeByParentCode(@Param("list")List<String> list);
}