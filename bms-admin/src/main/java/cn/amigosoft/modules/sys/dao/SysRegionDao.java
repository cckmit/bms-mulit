package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.dto.RegionDTO;
import cn.amigosoft.modules.sys.entity.SysRegionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysRegionDao extends BaseDao<SysRegionEntity> {

	List<SysRegionEntity> getList(Map<String, Object> params);

	List<SysRegionEntity> getListByLevel(Integer treeLevel);

	List<Map<String, Object>> getTreeList();

	SysRegionEntity getById(Long id);

	int getCountByPid(Long pid);

	RegionDTO selectRegionName(Map<String, Object> map);

	/**
	 * 根据省市名称查询对应id，id使用#号隔开
	 * @param provience 省
	 * @param city 市
	 * @param county 县
	 * @return
	 */
    String queryProvienceCityCountyId(String provience, String city, String county);

}
