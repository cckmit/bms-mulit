package cn.amigosoft.modules.bms.other.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.other.entity.BmsNoticeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通知表 
 */
@Mapper
public interface BmsNoticeDao extends BaseDao<BmsNoticeEntity> {
	
}