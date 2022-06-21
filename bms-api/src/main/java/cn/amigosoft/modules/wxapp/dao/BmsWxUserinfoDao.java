package cn.amigosoft.modules.wxapp.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.wxapp.dto.BmsWxUserinfoDTO;
import cn.amigosoft.modules.wxapp.entity.BmsWxUserinfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信用户信息
 */
@Mapper
public interface BmsWxUserinfoDao extends BaseDao<BmsWxUserinfoEntity> {

    BmsWxUserinfoDTO getwxUserInfoByOpenid(@Param("openId") String openId);

    int synchronizeData();

    List<BmsWxUserinfoDTO> selectNeedRemindOrderUser();

    List<BmsWxUserinfoDTO> selectTomorrowNoOrderUser();

}