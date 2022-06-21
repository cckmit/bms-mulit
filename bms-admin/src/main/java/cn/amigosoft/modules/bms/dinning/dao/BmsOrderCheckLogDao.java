package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderCheckLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单核销记录表 
 */
@Mapper
public interface BmsOrderCheckLogDao extends BaseDao<BmsOrderCheckLogEntity> {
	
}