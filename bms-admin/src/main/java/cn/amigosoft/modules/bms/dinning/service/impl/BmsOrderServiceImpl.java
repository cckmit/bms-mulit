package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dao.BmsMealTypeDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsOrderDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsOrderDetailDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderDetailEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderEntity;
import cn.amigosoft.modules.bms.dinning.excel.BmsOrderDetail2Excel;
import cn.amigosoft.modules.bms.dinning.service.BmsOrderService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysParamsDao;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.entity.SysParamsEntity;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单表
 */
@Service
public class BmsOrderServiceImpl extends CrudServiceImpl<BmsOrderDao, BmsOrderEntity, BmsOrderDTO> implements BmsOrderService {

    @Autowired
    private BmsOrderDetailDao orderDetailDao;

    @Autowired
    private BmsMealTypeDao mealTypeDao;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysParamsDao paramsDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsOrderEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsOrderEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public Result<PageData<BmsOrderDTO>> getPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        if (userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ORDER_VIEW_ALL) == 0) {
            if (userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ORDER_VIEW_DEPT) == 0) {
                if (userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ORDER_VIEW_CURRENT_USER) == 0) {
                    return new Result<PageData<BmsOrderDTO>>().ok(getPageData(new ArrayList<>(), 0, BmsOrderDTO.class));
                } else {
                    params.put("currentUser", userId);
                }
            } else {
                params.put("deptId", user.getDeptId());
            }
        }
        IPage<BmsOrderEntity> page = getPage(params, "o.create_date", false);
        List<BmsOrderDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsOrderDTO>>().ok(getPageData(resultList, page.getTotal(), BmsOrderDTO.class));
    }

    @Override
    public Result insertOrder(BmsOrderDTO dto) {
        List<BmsOrderDetailDTO> orderDetailList = dto.getOrderDetailList();
        if (orderDetailList == null || orderDetailList.size() == 0) {
            return new Result().error("未选择餐品");
        }

        DateTime currentDateTime = new DateTime();
        QueryWrapper<SysParamsEntity> queryParams = new QueryWrapper<>();
        queryParams.eq("param_code", BmsConstant.STOP_ORDER_CODE);
        SysParamsEntity params = paramsDao.selectOne(queryParams);
        if (params != null && BmsConstant.STOP_ORDER_VALUE_ON.equals(params.getParamValue())) {
            if (currentDateTime.getDayOfWeek() == 5 && Integer.parseInt(currentDateTime.toString("HH")) >= 11) {
                int weekOfWeekyear = currentDateTime.getWeekOfWeekyear();
                for (BmsOrderDetailDTO orderDetailDTO : orderDetailList) {
                    String eatDate = orderDetailDTO.getEatDate();
                    DateTime eatDateTime = new DateTime(eatDate);
                    if (eatDateTime.getWeekOfWeekyear() == weekOfWeekyear) {
                        int dayOfWeek = eatDateTime.getDayOfWeek();
                        if (dayOfWeek == 6 || dayOfWeek == 7) {
                            return new Result().error("周五11点后停止对本周末的报餐");
                        }
                    }
                }
            }
        }

        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        for (BmsOrderDetailDTO orderDetailDTO : orderDetailList) {
            String eatDate = orderDetailDTO.getEatDate();
            Long mealTypeId = orderDetailDTO.getMealTypeId();
            int count = orderDetailDao.selectUserDetailCount(creator, eatDate, mealTypeId);
            if (count > 0) {
                return new Result().error(eatDate + "的" + orderDetailDTO.getMealTypeName() + "已存在订购餐品");
            }
            BmsMealTypeEntity mealTypeEntity = mealTypeDao.selectById(mealTypeId);
            Integer advanceOrderTime = mealTypeEntity.getAdvanceOrderTime();
            String beginTime = mealTypeEntity.getBeginTime();
            Integer[] yyyyMMdd = split(eatDate, "-");
            Integer[] HHmm = split(beginTime, ":");
            // 可以提前订餐的最晚时间
            DateTime eatDateTime = new DateTime(yyyyMMdd[0], yyyyMMdd[1], yyyyMMdd[2], HHmm[0], HHmm[1], 0).minusHours(advanceOrderTime);
            if (currentDateTime.isAfter(eatDateTime)) {
                return new Result().error("所选餐品[" + orderDetailDTO.getItemName() + "]已超过" + orderDetailDTO.getMealTypeName() + "可订餐时间");
            }
        }
        dto.setType(BmsConstant.ORDER_TYPE_MEAL);
        super.save(dto);
        for (BmsOrderDetailDTO orderDetailDTO : orderDetailList) {
            BmsOrderDetailEntity orderDetailEntity = new BmsOrderDetailEntity();
            orderDetailEntity.setOrderId(dto.getId());
            orderDetailEntity.setItemId(orderDetailDTO.getItemId());
            orderDetailEntity.setEatDate(orderDetailDTO.getEatDate());
            orderDetailEntity.setStatus(BmsConstant.CANCEL_BEFORE_VERIFICATION);
            orderDetailEntity.setScanStatus(BmsConstant.CANCEL_BEFORE_VERIFICATION);
            orderDetailEntity.setMealTypeId(orderDetailDTO.getMealTypeId());
            orderDetailEntity.setCanteenId(orderDetailDTO.getCanteenId());
            orderDetailDao.insert(orderDetailEntity);
        }
        return new Result<>();
    }

    private Integer[] split(String str, String regex) {
        String[] arr = str.split(regex);
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }

    @Override
    public BmsOrderDTO selectOrderById(Long id) {
        BmsOrderDTO orderDTO = baseDao.selectOrderById(id);
        List<BmsOrderDetailDTO> list = orderDetailDao.selectOrderDetailByOrderId(id);
        orderDTO.setOrderDetailList(list);
        return orderDTO;
    }

    @Override
    public Result cancel(List<BmsOrderDetailDTO> list) {
        Result result = new Result();
        try {
            QueryWrapper<SysParamsEntity> queryParams = new QueryWrapper<>();
            queryParams.eq("param_code", BmsConstant.ORDER_DETAIL_CANCEL_COUNT);
            SysParamsEntity params = paramsDao.selectOne(queryParams);
            int paramsValue = Integer.parseInt(params.getParamValue());
            int currentCancelCount = 0;

            List<BmsOrderDetailDTO> cancelList = orderDetailDao.selectMonthCancelList(SecurityUser.getUser().getId());
            for (BmsOrderDetailDTO orderDetail : cancelList) {
                // 取消餐次应提前的小时数
                Integer advanceOrderTime = orderDetail.getAdvanceOrderTime();

                // 用餐日期转成字符串
                String eatDate = orderDetail.getEatDate();
                // 与用餐开始时间进行拼接
                String eatDateTime = eatDate.concat(" ").concat(orderDetail.getBeginTime()).concat(":00");
                // 转成Date类型
                DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date beginDateTime = dateTimeFormat.parse(eatDateTime);
                beginDateTime = new DateTime(beginDateTime).minusHours(advanceOrderTime).toDate();
                Date updateDate = orderDetail.getUpdateDate();
                if (updateDate != null && updateDate.after(beginDateTime)) {
                    currentCancelCount++;
                }
            }
            List<BmsOrderDetailDTO> cancelOrderDetailList = new ArrayList<>();
            for (BmsOrderDetailDTO orderDetail : list) {
                if (BmsConstant.CANCEL_AFTER_VERIFICATION.equals(orderDetail.getStatus()) || BmsConstant.CANCEL_AFTER_VERIFICATION.equals(orderDetail.getScanStatus())) {
                    return result.error(orderDetail.getCanteenName() + "的餐别" + orderDetail.getMealTypeName() + "的餐品" + orderDetail.getItemName() + "已核销");
                }
                // 取消餐次应提前的小时数
                Integer advanceOrderTime = orderDetail.getAdvanceOrderTime();
                //获取当前时间的Calendar，并在此基础上累加应提前的小时数，转化为Date
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, advanceOrderTime);
                Date contrastDate = calendar.getTime();

                // 用餐日期转成字符串
                String eatDate = orderDetail.getEatDate();
                // 与用餐开始时间进行拼接
                String eatDateTime = eatDate.concat(" ").concat(orderDetail.getBeginTime()).concat(":00");
                // 转成Date类型
                DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date beginDateTime = dateTimeFormat.parse(eatDateTime);

                //若当前时间加上应提前的小时数，在用餐开始时间之后，则为无法取消的餐次
                if (contrastDate.after(beginDateTime)) {
                    currentCancelCount++;
                    if (currentCancelCount > paramsValue) {
                        return result.error("已超过本月订单详情可临时取消次数：" + paramsValue);
                    }
                    cancelOrderDetailList.add(orderDetail);
                }
            }
            // 推送临时取消订餐通知
            if (cancelOrderDetailList.size() > 0) {
                List<SysUserDTO> sendMsgUserList = userDao.selectUserByPermission(BmsConstant.PERMISSION_RECEPTION_MEAL_GRANT);
                SysUserEntity user = userDao.selectById(SecurityUser.getUser().getId());
                for (BmsOrderDetailDTO dto : cancelOrderDetailList) {
                    String content = "【" + dto.getCanteenName() + "】" + user.getRealName() + "取消了" + dto.getEatDate() + "的" + dto.getMealTypeName() + "：" + dto.getItemName();
                    for (SysUserDTO userDTO : sendMsgUserList) {
                        wxService.sendWeixinTemplateMsg(userDTO.getId(), "尊敬的用户，您有新的【订餐取消】通知", content, null, "已取消");
                    }
                }
            }

            for (BmsOrderDetailDTO detailDTO : list) {
                BmsOrderDetailEntity orderDetailEntity = new BmsOrderDetailEntity();
                orderDetailEntity.setId(detailDTO.getId());
                orderDetailEntity.setDel(BmsConstant.DEL);
                orderDetailDao.updateById(orderDetailEntity);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error("删除服务异常");
        }

    }

    @Override
    public List<BmsOrderDTO> selectExportList(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        if (userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ORDER_VIEW_ALL) == 0) {
            if (userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ORDER_VIEW_DEPT) == 0) {
                if (userDao.checkUserHavePermission(userId, BmsConstant.PERMISSION_ORDER_VIEW_CURRENT_USER) == 0) {
                    return new ArrayList<>();
                } else {
                    params.put("currentUser", userId);
                }
            } else {
                params.put("deptId", user.getDeptId());
            }
        }
        List<BmsOrderDTO> list = baseDao.selectExportList(params);
        for (BmsOrderDTO orderDTO : list) {
            Long id = orderDTO.getId();
            List<BmsOrderDetail2Excel> detailList = orderDetailDao.selectExportDataByOrderId(id);
            orderDTO.setDetailList(detailList);
        }
        return list;
    }

    @Override
    public Result deleteOrderByIds(Long[] ids) {
        try {
            Result result = new Result();
            for (Long id : ids) {
                List<BmsOrderDetailDTO> orderDetailList = orderDetailDao.selectOrderDetailByOrderId(id);
                for (BmsOrderDetailDTO orderDetail : orderDetailList) {
                    if (BmsConstant.CANCEL_AFTER_VERIFICATION.equals(orderDetail.getStatus())) {
                        return result.error("报餐编号" + id + "存在已核销的餐次");
                    }
                    // 取消餐次应提前的小时数
                    Integer advanceOrderTime = orderDetail.getAdvanceOrderTime();
                    //获取当前时间的Calendar，并在此基础上累加应提前的小时数，转化为Date
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR_OF_DAY, advanceOrderTime);
                    Date contrastDate = calendar.getTime();

                    // 用餐日期转成字符串
                    String eatDate = orderDetail.getEatDate();
                    // 与用餐开始时间进行拼接
                    String eatDateTime = eatDate.concat(" ").concat(orderDetail.getBeginTime()).concat(":00");
                    // 转成Date类型
                    DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date beginDateTime = dateTimeFormat.parse(eatDateTime);

                    // 若未取消，且当前时间加上应提前的小时数，在用餐开始时间之后，则为无法取消的餐次
                    if (contrastDate.after(beginDateTime) && (!BmsConstant.DEL.equals(orderDetail.getDel()))) {
                        return result.error("报餐编号" + id + "存在已过期的餐次");
                    }
                }
            }
            List<Long> idsList = Arrays.asList(ids);
            deleteBatchIds(idsList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error("删除服务异常");
        }
    }
}
