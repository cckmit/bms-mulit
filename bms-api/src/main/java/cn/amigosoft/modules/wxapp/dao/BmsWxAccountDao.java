package cn.amigosoft.modules.wxapp.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.wxapp.entity.BmsWxAccountEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台帐号信息表
 */
@Mapper
public interface BmsWxAccountDao extends BaseDao<BmsWxAccountEntity> {

}
