package cn.amigosoft.modules.wxapp.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.wxapp.entity.WxappSessionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序会话
 */
@Mapper
public interface WxappSessionDao extends BaseDao<WxappSessionEntity> {

}
