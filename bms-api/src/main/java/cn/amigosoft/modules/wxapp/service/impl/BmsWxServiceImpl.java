package cn.amigosoft.modules.wxapp.service.impl;

import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.security.dao.SysUserTokenDao;
import cn.amigosoft.modules.security.entity.SysUserTokenEntity;
import cn.amigosoft.modules.security.oauth2.TokenGenerator;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import cn.amigosoft.modules.wxapp.client.WXAppRestTemplate;
import cn.amigosoft.modules.wxapp.client.entity.auth.Code2SessionReq;
import cn.amigosoft.modules.wxapp.client.entity.auth.Code2SessionRsp;
import cn.amigosoft.modules.wxapp.dao.BmsWxBindDao;
import cn.amigosoft.modules.wxapp.dao.BmsWxAccountDao;
import cn.amigosoft.modules.wxapp.dao.BmsWxUserinfoDao;
import cn.amigosoft.modules.wxapp.entity.BmsWxAccountEntity;
import cn.amigosoft.modules.wxapp.entity.BmsWxBindEntity;
import cn.amigosoft.modules.wxapp.entity.BmsWxUserinfoEntity;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import cn.amigosoft.modules.wxapp.utils.WxAES;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName : WXAppServiceImpl
 */
@Slf4j
@Service
public class BmsWxServiceImpl implements BmsWxService {

    @Autowired
    private BmsWxAccountDao wxappAccountDao;

    @Autowired
    private BmsWxBindDao appletBindDao;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysUserTokenDao userTokenDao;

    @Autowired
    private BmsWxUserinfoDao userinfoDao;

    private WxMpService wxMpService;

    private BmsWxAccountEntity officialAccount;

    private BmsWxAccountEntity appAccount;

    /**
     * 过期时间 单位：秒
     */
    private final static int EXPIRE = 3600 * 12;

    public BmsWxAccountEntity getWxappAccountEntity() {
        QueryWrapper<BmsWxAccountEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 0);
        List<BmsWxAccountEntity> wxappAccountEntitys = wxappAccountDao.selectList(wrapper);
        if (wxappAccountEntitys.size() == 0) {
            return null;
        }
        return wxappAccountEntitys.get(0);
    }

    /**
     * authWxapp
     * 1. code => 微信平台兑换sessionKey openId
     * 2. openId => 查绑定表
     * ①有数据
     * 生成token返回
     * ②无数据
     * 返回空数据
     */
    @Override
    public Result<Object> wxLogin(String code) {
        // 登录凭证不能为空
        if (StringUtils.isEmpty(code)) {
            return new Result<>();
        }
        try {
            Code2SessionRsp sessionRsp = getSessionRsp(code);
            if (sessionRsp == null) {
                return new Result<>();
            }
            // 用户的唯一标识（openid）
            String openId = sessionRsp.getOpenid();
            QueryWrapper<BmsWxBindEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("open_id", openId);
            List<BmsWxBindEntity> bindEntities = appletBindDao.selectList(queryWrapper);

            if (bindEntities.size() == 0) {
                return new Result<>();
            }
            BmsWxBindEntity appletBindEntity = bindEntities.get(0);
            Long userId = appletBindEntity.getUserId();
            if (userId == null) {
                return new Result<>();
            }
            String token = createToken(userId);
            appletBindEntity.setToken(token);
            String unionid = sessionRsp.getUnionid();
            appletBindEntity.setUnionId(unionid);
            appletBindDao.updateById(appletBindEntity);
            return new Result<>().ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>();
        }
    }

    private String createToken(Long userId) {
        //用户token
        String token;

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = userTokenDao.getByUserId(userId);
        if (tokenEntity == null) {
            //生成一个token
            token = TokenGenerator.generateValue();

            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateDate(now);
            tokenEntity.setExpireDate(expireTime);

            //保存token
            userTokenDao.insert(tokenEntity);
        } else {
            //判断token是否过期
            if (tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()) {
                //token过期，重新生成token
                token = TokenGenerator.generateValue();
            } else {
                token = tokenEntity.getToken();
            }

            tokenEntity.setToken(token);
            tokenEntity.setUpdateDate(now);
            tokenEntity.setExpireDate(expireTime);

            //更新token
            userTokenDao.updateById(tokenEntity);
        }
        return token;
    }

    /**
     * 1. code => 微信平台兑换sessionKey openId
     * 2. encryptedData + iv => 解析手机号
     * 手机号兑换userId
     * 3. sessionKey、openId、userId、手机号插入bind表
     * 4. 生成token返回
     */
    @Override
    public Result<Object> wxBind(String encryptedData, String iv, String code) {
        try {
            if (StringUtils.isEmpty(encryptedData) || StringUtils.isEmpty(iv) || StringUtils.isEmpty(code)) {
                return new Result<>();
            }
            Code2SessionRsp sessionRsp = getSessionRsp(code);
            if (sessionRsp == null) {
                return new Result<>();
            }
            String openId = sessionRsp.getOpenid();
            String sessionKey = sessionRsp.getSession_key();
            String unionid = sessionRsp.getUnionid();
            String json = WxAES.wxDecrypt(encryptedData, sessionKey, iv);
            String mobile = JSONObject.parseObject(json).getString("purePhoneNumber");
            QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mobile", mobile);
            // 测试环境可能会出现相同手机号的数据
            List<SysUserEntity> users = userDao.selectList(queryWrapper);
            if (users.size() == 0) {
                return new Result<>();
            }
            SysUserEntity userEntity = users.get(0);
            Long userId = userEntity.getId();
            String token = createToken(userId);
            QueryWrapper<BmsWxBindEntity> query = new QueryWrapper<>();
            query.eq("open_id", openId);
            List<BmsWxBindEntity> bindEntities = appletBindDao.selectList(query);
            if (bindEntities.size() == 0) {
                BmsWxBindEntity appletBindEntity = new BmsWxBindEntity();
                appletBindEntity.setUnionId(unionid);
                appletBindEntity.setOpenId(openId);
                appletBindEntity.setSessionKey(sessionKey);
                appletBindEntity.setUserId(userId);
                appletBindEntity.setMobile(mobile);
                appletBindEntity.setToken(token);
                appletBindEntity.setType(0);
                appletBindDao.insert(appletBindEntity);
            } else {
                BmsWxBindEntity appletBindEntity = bindEntities.get(0);
                appletBindEntity.setToken(token);
                appletBindEntity.setUnionId(unionid);
                appletBindDao.updateById(appletBindEntity);
            }
            return new Result<>().ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>();
        }
    }

    private Code2SessionRsp getSessionRsp(String code) {
        // 获取小程序平台账号信息
        BmsWxAccountEntity wxappAccountEntity = getWxappAccountEntity();
        if (wxappAccountEntity == null) {
            return null;
        }
        // 向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        Code2SessionReq code2SessionReq = new Code2SessionReq();
        code2SessionReq.setAppid(wxappAccountEntity.getAppId());
        code2SessionReq.setSecret(wxappAccountEntity.getAppSecret());
        code2SessionReq.setJs_code(code);
        Code2SessionRsp code2SessionRsp = new WXAppRestTemplate().auth_code2Session(code2SessionReq);
        if (code2SessionRsp.getErrcode() != null && !Integer.valueOf(0).equals(code2SessionRsp.getErrcode())) {
            log.error(String.format("Appid:%s,Secret:%s,code2Session errcode:%s,errmsg:%s。", wxappAccountEntity.getAppId(), wxappAccountEntity.getAppSecret(), code2SessionRsp.getErrcode(), code2SessionRsp.getErrmsg()));
            return null;
        }
        return code2SessionRsp;
    }

    private void initBaseData() {
        if (wxMpService == null) {
            List<BmsWxAccountEntity> list = wxappAccountDao.selectList(new QueryWrapper<>());
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

}
