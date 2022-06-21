package cn.amigosoft.modules.wxapp.corn;

import cn.amigosoft.modules.dining.dao.BmsHolidayDao;
import cn.amigosoft.modules.dining.entity.BmsHolidayEntity;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.wxapp.dao.BmsWxAccountDao;
import cn.amigosoft.modules.wxapp.dao.BmsWxUserinfoDao;
import cn.amigosoft.modules.wxapp.dto.BmsWxUserinfoDTO;
import cn.amigosoft.modules.wxapp.entity.BmsWxAccountEntity;
import cn.amigosoft.modules.wxapp.entity.BmsWxUserinfoEntity;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 粉丝数据同步
 */
@Component
@Slf4j
public class BmsWxTask {

    @Autowired
    private BmsWxAccountDao wxappAccountDao;

    @Autowired
    private BmsWxUserinfoDao wxUserinfoDao;

    @Autowired
    private BmsWxService wxService;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsHolidayDao holidayDao;

    private WxMpService wxMpService;

    private BmsWxAccountEntity officialAccount;

    private BmsWxAccountEntity appAccount;

    private WxMpTemplateMessage.MiniProgram miniProgram;

    private void initBaseData() {
        if (wxMpService == null) {
            QueryWrapper<BmsWxAccountEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("type", 1);
            List<BmsWxAccountEntity> list = wxappAccountDao.selectList(wrapper);
            if (list.size() != 0) {
                officialAccount = list.get(0);
                String appId = officialAccount.getAppId();
                WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
                configStorage.setAppId(appId);
                configStorage.setSecret(officialAccount.getAppSecret());
                wxMpService = new WxMpServiceImpl();
                wxMpService.setWxMpConfigStorage(configStorage);
            }
        }
        if (miniProgram == null) {
            QueryWrapper<BmsWxAccountEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("type", 0);
            List<BmsWxAccountEntity> list = wxappAccountDao.selectList(wrapper);
            if (list.size() != 0) {
                appAccount = list.get(0);
                String appId = appAccount.getAppId();
                miniProgram = new WxMpTemplateMessage.MiniProgram();
                miniProgram.setAppid(appId);
            }
        }
    }

    private List<WxMpUser> getUserList() throws WxErrorException {
        WxMpUserService userService = wxMpService.getUserService();
        List<String> openids = wxMpService.getUserService().userList(null).getOpenids();
        List<WxMpUser> result = new ArrayList<>();
        List<String> queryOpenIdList = new ArrayList<>();
        for (String openid : openids) {
            queryOpenIdList.add(openid);
            if (queryOpenIdList.size() == 100) {
                List<WxMpUser> userList = userService.userInfoList(queryOpenIdList);
                result.addAll(userList);
                queryOpenIdList.clear();
            }
        }
        if (queryOpenIdList.size() > 0) {
            List<WxMpUser> userList = userService.userInfoList(queryOpenIdList);
            result.addAll(userList);
            queryOpenIdList.clear();
        }
        for (WxMpUser user : result) {
            user.setNickname(null);
        }
        return result;
    }

    //    @Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(cron = "0 0 */3 * * ?")
    public void updateUserInfo() {
        log.info("开始更新公众号粉丝信息");
        initBaseData();
        try {
            List<WxMpUser> userList = getUserList();
            for (WxMpUser user : userList) {
                String openId = user.getOpenId();
                QueryWrapper<BmsWxUserinfoEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("open_id", openId);
                queryWrapper.eq("type", 1);

                BmsWxUserinfoEntity data = wxUserinfoDao.selectOne(queryWrapper);

                BmsWxUserinfoEntity entity = new BmsWxUserinfoEntity();
                entity.setCity(user.getCity());
                entity.setCountry(user.getCountry());
                entity.setGender(user.getSex());
                entity.setLanguage(user.getLanguage());
                entity.setOpenId(openId);
                entity.setUnionId(user.getUnionId());
                entity.setType(1);
                if (data == null) {
                    wxUserinfoDao.insert(entity);
                } else {
                    entity.setId(data.getId());
                    wxUserinfoDao.updateById(entity);
                }
            }
            wxUserinfoDao.synchronizeData();
            log.info("更新公众号粉丝信息结束");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新公众号粉丝信息异常", e);
        }
    }

    @Scheduled(cron = "0 30 8 ? * FRI")
    public void orderTask() {
        log.info("开始推送报餐提醒");
        remindOrder();
        log.info("推送报餐提醒结束");
    }

    @Scheduled(cron = "0 30 9 ? * FRI")
    public void orderTask2() {
        log.info("开始推送报餐提醒");
        remindOrder();
        log.info("推送报餐提醒结束");
    }

    @Scheduled(cron = "0 30 10 ? * FRI")
    public void orderTask3() {
        log.info("开始推送报餐提醒");
        remindOrder();
        log.info("推送报餐提醒结束");
    }

    @Scheduled(cron = "0 30 10 * * *")
    public void orderTask4() {
        log.info("开始推送明日未报餐提醒");
        remindTomorrowNoOrderUser();
        log.info("推送明日未报餐提醒结束");
    }

    private boolean checkNextWeekIsHoliday() {
        List<String> list = new ArrayList<>();
        // 下周一
        DateTime dateTime = new DateTime().plusWeeks(1).withDayOfWeek(1);
        String dateFormat = "yyyy-MM-dd";
        list.add(dateTime.toString(dateFormat));
        for (int i = 1; i <= 6; i++) {
            list.add(dateTime.plusDays(i).toString(dateFormat));
        }
        QueryWrapper<BmsHolidayEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("holiday", list);
        Integer count = holidayDao.selectCount(queryWrapper);
        return count == 7;
    }

    private boolean checkTomorrowIsHoliday() {
        QueryWrapper<BmsHolidayEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("holiday", new DateTime().plusDays(1).toString("yyyy-MM-dd"));
        Integer count = holidayDao.selectCount(queryWrapper);
        return count > 0;
    }

    private void remindOrder() {
        // 校验下周是否均为节假日
        if (checkNextWeekIsHoliday()) {
            return;
        }
        initBaseData();
        List<BmsWxUserinfoDTO> list = wxUserinfoDao.selectNeedRemindOrderUser();
        for (BmsWxUserinfoDTO userInfo : list) {
            String openId = userInfo.getOpenId();
            try {
                wxService.sendTemplateMsg(openId, "尊敬的用户，您有新的【报餐】提醒", "您下周还未报餐，请及时报餐。工作再忙，也要按时吃饭~", "pages/dining/order/index", "待报餐");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void remindTomorrowNoOrderUser() {
        // 校验明天是否为节假日
        if (checkTomorrowIsHoliday()) {
            return;
        }

        initBaseData();
        List<BmsWxUserinfoDTO> list = wxUserinfoDao.selectTomorrowNoOrderUser();
        for (BmsWxUserinfoDTO userInfo : list) {
            String openId = userInfo.getOpenId();
            try {
                wxService.sendTemplateMsg(openId, "尊敬的用户，您有新的【报餐】提醒", "您明日还未报餐，请及时报餐。工作再忙，也要按时吃饭~", "pages/dining/order/index", "待报餐");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
