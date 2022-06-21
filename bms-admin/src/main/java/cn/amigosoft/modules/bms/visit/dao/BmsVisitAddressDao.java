package cn.amigosoft.modules.bms.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitAddressEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访问地点表 
 */
@Mapper
public interface BmsVisitAddressDao extends BaseDao<BmsVisitAddressEntity> {
	
}