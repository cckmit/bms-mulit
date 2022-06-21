package cn.amigosoft.modules.bms.weixin.service;

import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

public interface BmsWxService {

    boolean sendWeixinTemplateMsg(Long userId, String title, String content, String pagePath, String status);

    void sendTemplateMsg(String openId, String title, String content, String pagePath, String status) throws WxErrorException;

    void deleteInfoByUserIds(List<Long> userIds);

    String getWeixinQrCode(String sceneStr, String pagePath);
}
