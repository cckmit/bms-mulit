package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.dining.dao.BmsOrderDetailDao;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailInfoDTO;
import cn.amigosoft.modules.dining.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.dining.entity.BmsOrderDetailEntity;
import cn.amigosoft.modules.dining.service.BmsMealTypeService;
import cn.amigosoft.modules.dining.service.BmsOrderDetailService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysParamsDao;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.entity.SysParamsEntity;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import cn.amigosoft.modules.wxapp.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单详情表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Service
public class BmsOrderDetailServiceImpl extends CrudServiceImpl<BmsOrderDetailDao, BmsOrderDetailEntity, BmsOrderDetailDTO> implements BmsOrderDetailService {

    @Autowired
    private SysParamsDao paramsDao;

    @Autowired
    private BmsWxService wxService;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private BmsMealTypeService mealTypeService;

    @Override
    public QueryWrapper<BmsOrderDetailEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<BmsOrderDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    //获取报餐订单详情
    @Override
    public List<BmsOrderDetailInfoDTO> getDetailInfo(Long id) {
        try {
            // 先获取订单编号对应的用餐日期集合
            List<BmsOrderDetailInfoDTO> detailInfoDTOList = baseDao.getOrderDateList(id);
            // 遍历，获取订单编号与用餐日期一同对应的餐次
            for (BmsOrderDetailInfoDTO detailInfoDTO : detailInfoDTOList) {
                String eatDate = detailInfoDTO.getEatDate();
                List<BmsOrderDetailDTO> detailDTOList = baseDao.getOrderDetailList(id, eatDate);
                for (BmsOrderDetailDTO detailDTO : detailDTOList) {
                    Integer status = detailDTO.getStatus();   //0：未核销 1：已核销  一卡通核销
                    Integer scanStatus = detailDTO.getScanStatus(); //0：未核销 1：已核销  扫码核销
                    Integer del = detailDTO.getDel();         //0：未删除 1：已删除
                    // 获取用餐日期
                    String detailEatDate = detailDTO.getEatDate();
                    // 与用餐结束时间进行拼接，得到用餐结束时间
                    String eatDateTime = detailEatDate.concat(" ").concat(detailDTO.getEndTime()).concat(":00");
                    // 转成Date类型
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date endDateTime = format.parse(eatDateTime);
                    // 获取当前时间
                    String nowadaysTime = format.format(new Date());
                    Date nowDate = format.parse(nowadaysTime);

                    /*if (BmsConstant.ORDER_PAID.equals(status)) {
                        // 若状态为已核销，则为已核销
                        detailDTO.setRealStatus(BmsConstant.ORDER_PAID);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && BmsConstant.DEL.equals(del)) {
                        // 若状态为未核销，且已删除，则为已取消
                        detailDTO.setRealStatus(BmsConstant.ORDER_CANCEL);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && BmsConstant.NOT_DEL.equals(del)
                            && nowDate.after(endDateTime)) {
                        // 若状态为未核销，未删除,且当前时间在用餐结束时间之后，则为已过期
                        detailDTO.setRealStatus(BmsConstant.ORDER_INVALID);
                    } else {
                        detailDTO.setRealStatus(status);
                    }*/

                    if (BmsConstant.ORDER_PAID.equals(status) || BmsConstant.ORDER_PAID.equals(scanStatus)) {
                        // 若状态为已核销，则为已核销
                        detailDTO.setRealStatus(BmsConstant.ORDER_PAID);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && BmsConstant.ORDER_NOT_PAID.equals(scanStatus)
                            && BmsConstant.DEL.equals(del)) {
                        // 若状态为未核销，且已删除，则为已取消
                        detailDTO.setRealStatus(BmsConstant.ORDER_CANCEL);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && BmsConstant.ORDER_NOT_PAID.equals(scanStatus)
                            && BmsConstant.NOT_DEL.equals(del) && nowDate.after(endDateTime)) {
                        // 若状态为未核销，未删除,且当前时间在用餐结束时间之后，则为已过期
                        detailDTO.setRealStatus(BmsConstant.ORDER_INVALID);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && BmsConstant.ORDER_NOT_PAID.equals(scanStatus)
                            && BmsConstant.NOT_DEL.equals(del) && nowDate.before(endDateTime)) {
                        // 若状态为未核销，未删除，且当前时间在用餐结束时间之前，则为未核销
                        detailDTO.setRealStatus(BmsConstant.ORDER_NOT_PAID);
                    } else {
                        detailDTO.setRealStatus(status);
                    }
                }
                detailInfoDTO.setOrderDetailList(detailDTOList);
            }
            // 返回集合包含：订单ID、用餐日期、以及二者对应的餐次
            return detailInfoDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取报餐订单对应的餐次状态与时间信息
    @Override
    public List<BmsOrderDetailDTO> getOrderStatusList(Long orderId) {
        List<BmsOrderDetailDTO> detailDTOList = baseDao.getOrderStatusList(orderId);
        return detailDTOList;
    }

    //（取消）删除报餐订单中的某一餐
    @Override
    public Result delete(Long id) {
        Result result = new Result();
        try {
            // 查询可临时取消的次数
            QueryWrapper<SysParamsEntity> queryParams = new QueryWrapper<>();
            queryParams.eq("param_code", BmsConstant.ORDER_DETAIL_CANCEL_COUNT);
            SysParamsEntity params = paramsDao.selectOne(queryParams);
            int paramsValue = Integer.parseInt(params.getParamValue());
            int currentCancelCount = 0;

            //获取用户当月取消用餐的记录，进而获取到临时取消的次数
            List<BmsOrderDetailDTO> cancelList = baseDao.selectMonthCancelList(SecurityUser.getUser().getId());
            for (BmsOrderDetailDTO cancelDTO : cancelList) {
                // 取消餐次应提前的小时数
                Integer advanceOrderTime = cancelDTO.getAdvanceOrderTime();
                // 获取用餐日期字符串
                String eatDate = cancelDTO.getEatDate();
                // 与用餐开始时间进行拼接
                String eatDateTime = eatDate.concat(" ").concat(cancelDTO.getBeginTime()).concat(":00");
                // 转成Date类型
                DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date beginDateTime = dateTimeFormat.parse(eatDateTime);

                beginDateTime = new DateTime(beginDateTime).minusHours(advanceOrderTime).toDate();
                Date updateDate = cancelDTO.getUpdateDate();
                if (updateDate != null && updateDate.after(beginDateTime)) {
                    currentCancelCount++;
                }
            }

            // 根据id查询出餐次的状态与时间信息
            BmsOrderDetailDTO detailDTO = baseDao.getOrderDetailStatus(id);
            // 若状态为已核销，即无法取消，需判断一卡通和扫码核销两种情况
            if (BmsConstant.ORDER_PAID.equals(detailDTO.getStatus())) {
                return result.error("该餐次已通过一卡通核销，无法取消");
            }
            if (BmsConstant.ORDER_PAID.equals(detailDTO.getScanStatus())) {
                return result.error("该餐次已通过扫码核销，无法取消");
            }
            // 获取用餐日期字符串
            String eatDate = detailDTO.getEatDate();
            // 与用餐开始时间进行拼接
            String eatDateTime = eatDate.concat(" ").concat(detailDTO.getBeginTime()).concat(":00");
            // 转成Date类型
            DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginDateTime = dateTimeFormat.parse(eatDateTime);

            // 取消餐次应提前的小时数
            Integer advanceOrderTime = detailDTO.getAdvanceOrderTime();
            //获取当前时间的Calendar，并在此基础上累加应提前的小时数，转化为Date
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, advanceOrderTime);
            Date contrastDate = calendar.getTime();

            //若当前时间加上应提前的小时数，在用餐开始时间之后，
            //并且临时取消次数已用完，则为无法取消的餐次
            if (contrastDate.after(beginDateTime)) {
                currentCancelCount++;
                if (currentCancelCount > paramsValue) {
                    return result.error("已超过本月订单详情可临时取消次数： " + paramsValue);
                }
                SysUserEntity user = userDao.selectById(SecurityUser.getUser().getId());
                List<SysUserDTO> list = userDao.selectUserByPermission(BmsConstant.PERMISSION_TEMP_CANCEL_NOTICE);
                String content ="【" + detailDTO.getCanteenName() + "】" + user.getRealName() + "取消了" + eatDate + "的" + detailDTO.getTypeName() + "：" + detailDTO.getItemName();
                for (SysUserDTO userDTO : list) {
                    wxService.sendWeixinTemplateMsg(userDTO.getId(), "尊敬的用户，您有新的【订餐取消】通知", content, null, "已取消");
                }
            }
            detailDTO.setDel(BmsConstant.DEL);
            this.update(detailDTO);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error("取消用餐服务异常");
        }
    }

    //核销列表分页查询
    @Override
    public PageData<BmsOrderDetailDTO> paidPage(Map<String, Object> params) {
        IPage<BmsOrderDetailEntity> page = getPage(params, "a.eat_date", false);
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        List<BmsOrderDetailDTO> list = baseDao.paidPage(params);
        String realStatus = (String) params.get("realStatus");
        if (StringUtil.isEmpty(realStatus)) {
            try {
                for (BmsOrderDetailDTO detailDTO : list) {
                    // 获取状态：0：未核销 1：已核销
                    Integer status = detailDTO.getStatus();
                    // 获取用餐日期
                    /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String eatDate = dateFormat.format(detailDTO.getEatDate());*/
                    String eatDate = detailDTO.getEatDate();
                    // 与用餐结束时间进行拼接
                    String eatDateTime = eatDate.concat(" ").concat(detailDTO.getEndTime()).concat(":00");
                    //转成data类型
                    DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date endDateTime = dateTimeFormat.parse(eatDateTime);
                    // 获取当前时间
                    Date nowDate = new Date();
                    // 核销状态判断
                    if (BmsConstant.ORDER_PAID.equals(status)) {
                        // status为1，即已核销
                        detailDTO.setRealStatus(BmsConstant.ORDER_PAID);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && nowDate.before(endDateTime)) {
                        // status为0，且当前时间在用餐结束时间之前，为未核销
                        detailDTO.setRealStatus(BmsConstant.ORDER_NOT_PAID);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && nowDate.after(endDateTime)) {
                        // status为0，且当前时间在用餐结束时间之后，为已过期
                        detailDTO.setRealStatus(BmsConstant.ORDER_INVALID);
                    } else {
                        detailDTO.setRealStatus(status);
                    }
                }
                //return getPageData(list, page.getTotal(), BmsOrderDetailDTO.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            for (BmsOrderDetailDTO detailDTO : list) {
                detailDTO.setRealStatus(Integer.valueOf(realStatus));
            }
        }
        return getPageData(list, page.getTotal(), BmsOrderDetailDTO.class);
    }

    //扫码核销记录列表分页查询
    @Override
    public PageData<BmsOrderDetailDTO> scanPaidPage(Map<String, Object> params) {
        IPage<BmsOrderDetailEntity> page = getPage(params, "a.eat_date", false);
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        List<BmsOrderDetailDTO> list = baseDao.scanPaidPage(params);
        String realStatus = (String) params.get("realStatus");
        if (StringUtil.isEmpty(realStatus)) {
            try {
                for (BmsOrderDetailDTO detailDTO : list) {
                    // 获取状态：0：未核销 1：已核销
                    Integer status = detailDTO.getScanStatus();
                    // 获取用餐日期
                    String eatDate = detailDTO.getEatDate();
                    // 与用餐结束时间进行拼接
                    String eatDateTime = eatDate.concat(" ").concat(detailDTO.getEndTime()).concat(":00");
                    //转成data类型
                    DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date endDateTime = dateTimeFormat.parse(eatDateTime);
                    // 获取当前时间
                    Date nowDate = new Date();
                    // 核销状态判断
                    if (BmsConstant.ORDER_PAID.equals(status)) {
                        // status为1，即已核销
                        detailDTO.setRealStatus(BmsConstant.ORDER_PAID);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && nowDate.before(endDateTime)) {
                        // status为0，且当前时间在用餐结束时间之前，为未核销
                        detailDTO.setRealStatus(BmsConstant.ORDER_NOT_PAID);
                    } else if (BmsConstant.ORDER_NOT_PAID.equals(status) && nowDate.after(endDateTime)) {
                        // status为0，且当前时间在用餐结束时间之后，为已过期
                        detailDTO.setRealStatus(BmsConstant.ORDER_INVALID);
                    } else {
                        detailDTO.setRealStatus(status);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            for (BmsOrderDetailDTO detailDTO : list) {
                detailDTO.setRealStatus(Integer.valueOf(realStatus));
            }
        }
        return getPageData(list, page.getTotal(), BmsOrderDetailDTO.class);
    }

    //核销详情信息查询
    @Override
    public BmsOrderDetailDTO getPaidDetailInfo(Long id) {
        BmsOrderDetailDTO dto = baseDao.getPaidDetailInfo(id);
        return dto;
    }

    //扫码核销详情信息
    @Override
    public BmsOrderDetailDTO getScanDetailInfo(Map<String, Object> params) {
        DateTime dateTime = new DateTime();
        String nowStr = dateTime.toString("yyyy-MM-dd HH:mm:ss");
        String nowDate = dateTime.toString("yyyy-MM-dd");
        String currentTimeStr = dateTime.toString("HH:mm");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        // 获取所有的上线中的餐别，以开始用餐时间正序排列
        QueryWrapper<BmsMealTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        //wrapper.eq("name", "午餐");
        wrapper.orderByAsc("begin_time");
        List<BmsMealTypeEntity> mealTypeList = mealTypeService.selectList(wrapper);
        // 根据当前时间进行判断，处在哪个餐别的用餐范围内，就查询出符合条件的最近一单的订单信息
        for (int i = 0; i < mealTypeList.size(); i++) {
            BmsMealTypeEntity mealTypeEntity = mealTypeList.get(i);
            DateTime beginTime = DateTime.parse(mealTypeEntity.getBeginTime(), formatter);
            DateTime endTime = DateTime.parse(mealTypeEntity.getEndTime(), formatter);
            DateTime currentTime = DateTime.parse(currentTimeStr, formatter);
            if (currentTime.isAfter(beginTime) && currentTime.isBefore(endTime)) {
                params.put("mealTypeId", mealTypeEntity.getId());
                break;
            }
        }
        if (StringUtil.isEmpty(params.get("mealTypeId"))) {
            return null;
        }
        // 返回该订单信息
        params.put("eatDate", nowDate);
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        BmsOrderDetailDTO dto = baseDao.getScanDetailInfo(params);
        return dto;
    }

    //报餐人数统计分页查询
    @Override
    public PageData<BmsOrderDetailDTO> countPage(Map<String, Object> params) {
        IPage<BmsOrderDetailEntity> page = getPage(params, " x.eatDate", false);
        List<BmsOrderDetailDTO> list = baseDao.countPage(params);
        return getPageData(list, page.getTotal(), BmsOrderDetailDTO.class);
    }

    @Override
    public List<Long> onOrderItemIds() {
        DateTime dateTime = new DateTime();
        int dayOfWeek = Integer.parseInt(dateTime.dayOfWeek().getAsString());
        List<String> eatDateList = new ArrayList<>();
        if (dayOfWeek == 7) {
            for (int i = 1; i <= 7; i++) {
                eatDateList.add(dateTime.plusDays(i).toString("yyyy-MM-dd"));
            }
        } else {
            for (int j = 0; j < 7; j++) {
                int x = j - (dayOfWeek + 6) % 7;
                eatDateList.add(dateTime.plusDays(x).toString("yyyy-MM-dd"));
            }
        }
        // 查询参数封装
        Map<String, Object> params = new HashMap<>();
        params.put("eatDateList", eatDateList);
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        return baseDao.onOrderItemIds(params);
    }

    //获取用户上一个订单中最新的报餐食堂
    @Override
    public BmsOrderDetailDTO lastCanteen() {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        BmsOrderDetailDTO dto = baseDao.lastCanteen(creator);
        return dto;
    }
}
