package cn.amigosoft.modules.bms.weixin.service.impl;

import cn.amigosoft.common.utils.HttpUtil;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.weixin.dao.BmsWxAccountDao;
import cn.amigosoft.modules.bms.weixin.dao.BmsWxBindDao;
import cn.amigosoft.modules.bms.weixin.dao.BmsWxUserinfoDao;
import cn.amigosoft.modules.bms.weixin.entity.BmsWxAccountEntity;
import cn.amigosoft.modules.bms.weixin.entity.BmsWxBindEntity;
import cn.amigosoft.modules.bms.weixin.entity.BmsWxUserinfoEntity;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.oss.cloud.OSSFactory;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

@Slf4j
@Service
public class BmsWxServiceImpl implements BmsWxService {

    @Autowired
    private BmsWxAccountDao wxAccountDao;

    @Autowired
    private BmsWxUserinfoDao userinfoDao;

    @Autowired
    private BmsWxBindDao bindDao;

    private WxMpService wxMpService;

    private BmsWxAccountEntity officialAccount;

    private BmsWxAccountEntity appAccount;

    private void initBaseData() {
        if (wxMpService == null) {
            List<BmsWxAccountEntity> list = wxAccountDao.selectList(new QueryWrapper<>());
            for (BmsWxAccountEntity entity : list) {
                Integer type = entity.getType();
                if (type == 0) {
                    appAccount = entity;
                } else if (type == 1) {
                    officialAccount = entity;
                    String appId = officialAccount.getAppId();
                    WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
                    configStorage.setAppId(appId);
                    configStorage.setSecret(officialAccount.getAppSecret());
                    wxMpService = new WxMpServiceImpl();
                    wxMpService.setWxMpConfigStorage(configStorage);
                }
            }
        }
    }

    @Override
    public boolean sendWeixinTemplateMsg(Long userId, String title, String content, String pagePath, String status) {
        try {
            initBaseData();
            QueryWrapper<BmsWxUserinfoEntity> queryUserInfo = new QueryWrapper<>();
            queryUserInfo.eq("user_id", userId);
            queryUserInfo.eq("type", BmsConstant.WX_TYPE_OFFICIAL_ACCOUNT);
            BmsWxUserinfoEntity userinfoEntity = userinfoDao.selectOne(queryUserInfo);
            if (userinfoEntity == null) {
                return false;
            }
            String openId = userinfoEntity.getOpenId();
            sendTemplateMsg(openId, title, content, pagePath, status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void sendTemplateMsg(String openId, String title, String content, String pagePath, String status) throws WxErrorException {
        initBaseData();
        WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid(appAccount.getAppId());
        if (StringUtils.isNotEmpty(pagePath)) {
            miniProgram.setPagePath(pagePath);
            miniProgram.setUsePath(false);
        }
        // 发送模板消息接口
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                // 接收者openid
                .toUser(openId)
                // 模板id
                .templateId(BmsConstant.WX_TEMPLATE_ID)
                // 模板跳转链接
                .miniProgram(miniProgram)
                .build();
        // 添加模板数据
        String date = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        templateMessage.addData(new WxMpTemplateData("first", title))
                .addData(new WxMpTemplateData("keyword1", date))
                .addData(new WxMpTemplateData("keyword2", content))
                .addData(new WxMpTemplateData("keyword3", status));
        if (StringUtils.isNotEmpty(pagePath)) {
            templateMessage.addData(new WxMpTemplateData("remark", "点击查看详情"));
        }
        // 发送模板消息
        wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        log.info("............................................................");
        log.info(openId);
        log.info(date);
        log.info(title);
        log.info(content);
        log.info(pagePath);
        log.info(status);
        log.info("............................................................");
    }

    @Override
    public void deleteInfoByUserIds(List<Long> userIds) {
        for (Long userId : userIds) {
            QueryWrapper<BmsWxBindEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            bindDao.delete(queryWrapper);
            QueryWrapper<BmsWxUserinfoEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            userinfoDao.delete(wrapper);
        }
    }

    private String getAppAccessToken() {
        try {
            initBaseData();
            String accessToken = appAccount.getAccessToken();
            if (StringUtils.isNotEmpty(accessToken)) {
                Date tokenInvalidTime = appAccount.getTokenInvalidTime();
                if (System.currentTimeMillis() < tokenInvalidTime.getTime()) {
                    return accessToken;
                }
            }
            //请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            String json = HttpUtil.doGet(
                    "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                            + appAccount.getAppId()
                            + "&secret="
                            + appAccount.getAppSecret(), null);
            System.out.println(json);
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (StringUtils.isNotEmpty(jsonObject.getString("errcode"))) {
                return null;
            }
            accessToken = jsonObject.getString("access_token");
            Integer expiresIn = jsonObject.getInteger("expires_in");
            appAccount.setAccessToken(accessToken);
            appAccount.setTokenInvalidTime(new DateTime().plusSeconds(expiresIn).toDate());
            wxAccountDao.updateById(appAccount);
            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String getWeixinQrCode(String sceneStr, String pagePath) {
        RestTemplate rest = new RestTemplate();
        try {
            String accessToken = getAppAccessToken();
            if (StringUtils.isEmpty(accessToken)) {
                return null;
            }
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", pagePath);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            log.info("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            byte[] result = entity.getBody();
            log.info(Base64.encodeBase64String(result));
            return OSSFactory.build().uploadSuffix(new ByteArrayInputStream(result), "png", "image/png");
        } catch (Exception e) {
            log.error("调用小程序生成微信永久小程序码URL接口异常", e);
        }
        return null;
    }


}
