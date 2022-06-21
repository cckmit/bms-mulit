package cn.amigosoft.modules.wxapp.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.wxapp.entity.BmsWxBindEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 绑定表
 */
@Mapper
public interface BmsWxBindDao extends BaseDao<BmsWxBindEntity> {
	
}