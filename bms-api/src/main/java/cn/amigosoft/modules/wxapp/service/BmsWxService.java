package cn.amigosoft.modules.wxapp.service;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.wxapp.dto.WxappAuthSessionDTO;
import cn.amigosoft.modules.wxapp.entity.WxappSessionEntity;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @ClassName : WXAppService
 * @Description : 微信小程序云调用服务
 */
public interface BmsWxService {

    Result<Object> wxLogin(String code);

    Result<Object> wxBind(String encryptedData, String iv, String code);

    boolean sendWeixinTemplateMsg(Long userId, String title, String content, String pagePath, String status);

    void sendTemplateMsg(String openId, String title, String content, String pagePath, String status) throws WxErrorException;

}
