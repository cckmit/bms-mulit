package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.entity.BmsDishesTypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品类别表 
 */
@Mapper
public interface BmsDishesTypeDao extends BaseDao<BmsDishesTypeEntity> {
	
}