package cn.amigosoft.modules.wxapp.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.wxapp.dto.WxappQrcodeDTO;
import cn.amigosoft.modules.wxapp.entity.WxappQrcodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序二维码生成
 */
@Mapper
public interface WxappQrcodeDao extends BaseDao<WxappQrcodeEntity> {
    WxappQrcodeDTO getWxappQrcode(Integer type);
}
