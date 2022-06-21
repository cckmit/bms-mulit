package cn.amigosoft.modules.bms.weixin.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.weixin.entity.BmsWxAccountEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台帐号信息表
 */
@Mapper
public interface BmsWxAccountDao extends BaseDao<BmsWxAccountEntity> {

}
