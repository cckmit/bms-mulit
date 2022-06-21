package cn.amigosoft.modules.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyAddressEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 申请和地点关联表 
 */
@Mapper
public interface BmsVisitApplyAddressDao extends BaseDao<BmsVisitApplyAddressEntity> {
	
}