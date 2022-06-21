package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealVisitorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接待餐访客关联表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@Mapper
public interface BmsReceptionMealVisitorDao extends BaseDao<BmsReceptionMealVisitorEntity> {
	
}