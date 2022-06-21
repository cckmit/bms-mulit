package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.dining.dao.BmsMealTypeDao;
import cn.amigosoft.modules.dining.dao.BmsOrderDao;
import cn.amigosoft.modules.dining.dao.BmsOrderDetailDao;
import cn.amigosoft.modules.dining.dto.BmsEatDateDTO;
import cn.amigosoft.modules.dining.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.dining.entity.BmsMealTypeEntity;
import cn.amigosoft.modules.dining.entity.BmsOrderDetailEntity;
import cn.amigosoft.modules.dining.entity.BmsOrderEntity;
import cn.amigosoft.modules.dining.service.BmsOrderDetailService;
import cn.amigosoft.modules.dining.service.BmsOrderService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysParamsDao;
import cn.amigosoft.modules.sys.entity.SysParamsEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
    private BmsOrderDetailService orderDetailService;

    @Autowired
    private BmsOrderDetailDao orderDetailDao;

    @Autowired
    private BmsMealTypeDao mealTypeDao;

    @Autowired
    private SysParamsDao paramsDao;

    @Override
    public QueryWrapper<BmsOrderEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<BmsOrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    /**
     * 报餐记录列表查询
     */
    @Override
    public PageData<BmsOrderDTO> queryPage(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        IPage<BmsOrderEntity> page = getPage(params, "a.create_date", false);
        List<BmsOrderDTO> list = baseDao.queryPage(params);
        return getPageData(list, page.getTotal(), BmsOrderDTO.class);
    }

    /**
     * 获取报餐订单详情信息
     */
    @Override
    public BmsOrderDTO getBaseInfo(Long id) {
        BmsOrderDTO orderDTO = baseDao.getBaseInfo(id);
        return orderDTO;
    }

    /**
     * 删除报餐订单
     */
    @Override
    public Result delete(Long id) {
        try {
            Result result = new Result();
            // 获取当前订单号对应的所有餐次
            List<BmsOrderDetailDTO> detailDTOList = orderDetailService.getOrderStatusList(id);
            // 遍历，若存在已核销或已过用餐开始时限的餐次，则无法删除
            for (BmsOrderDetailDTO detailDTO : detailDTOList) {
                if (BmsConstant.ORDER_PAID.equals(detailDTO.getStatus())) {
                    return result.error("存在已通过一卡通核销的餐次");
                }
                if (BmsConstant.ORDER_PAID.equals(detailDTO.getScanStatus())) {
                    return result.error("存在已通过扫码核销的餐次");
                }

                // 取消餐次应提前的小时数
                Integer advanceOrderTime = detailDTO.getAdvanceOrderTime();
                //获取当前时间的Calendar，并在此基础上累加应提前的小时数，转化为Date
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, advanceOrderTime);
                Date contrastDate = calendar.getTime();

                // 用餐日期转成字符串
                String eatDate = detailDTO.getEatDate();
                // 与用餐开始时间进行拼接
                String eatDateTime = eatDate.concat(" ").concat(detailDTO.getBeginTime()).concat(":00");
                // 转成Date类型
                DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date beginDateTime = dateTimeFormat.parse(eatDateTime);

                //若当前时间加上应提前的小时数，在用餐开始时间之后，
                //且未删除、未核销（前面已筛查过已核销的餐次），
                //则为已过期，即无法取消的餐次
                if (contrastDate.after(beginDateTime) && BmsConstant.NOT_DEL.equals(detailDTO.getDel())) {
                    return result.error("存在已过期的餐次");
                }
            }
            BmsOrderDTO orderDTO = this.get(id);
            orderDTO.setDel(BmsConstant.DEL);
            this.update(orderDTO);
            // baseDao.updateDelDetails(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error("删除服务异常");
        }
    }

    /**
     * 传入餐品ID，获取该餐品的可报餐日期集合
     */
    @Override
    public List<BmsEatDateDTO> chooseEatDates(BmsMealTypeDTO dto) {
        Long mealTypeId = dto.getId();
        List<String> eatDateDTOS = dto.getEatDateList();
        // 获取当天日期，和对应的周几
        DateTime dateTime = new DateTime();   //当天日期
        int today = dateTime.getDayOfWeek();  //当前周几，int类型
        // 查询参数
        DateTime beginDate = null;   //开始日期
        DateTime endDate = null;     //结束日期
        // 循环参数
        int beginNumber = 0;
        int endNumber = 0;
        // 如果当天为周五到周日，开始时间为当天，结束时间为下周周日
        if (today >= 5 && today <= 7) {
            // 获取当天日期和下周周日日期
            beginDate = dateTime;
            endDate = dateTime.plusDays(7 - today).plusWeeks(1);
            // 循环参数赋值
            beginNumber = 0;
            endNumber = Days.daysBetween(beginDate, endDate).getDays() + 1;
        }
        // 如果当天为周一到周四，开始时间为当天，结束时间为当周周日
        if (today >= 1 && today <= 4) {
            // 获取当天日期和周日日期
            beginDate = dateTime;
            endDate = dateTime.plusDays(7 - today);
            // 循环参数赋值
            beginNumber = 0;
            endNumber = 8 - today;
        }
        // 查询参数封装
        Map<String, Object> params = new HashMap<>();
        params.put("beginDate", beginDate.toString("yyyy-MM-dd"));
        params.put("endDate", endDate.toString("yyyy-MM-dd"));
        params.put("mealTypeId", mealTypeId);
        UserDetail user = SecurityUser.getUser();
        Long creator = user.getId();
        params.put("creator", creator);
        // 获取该餐品类别的已订购的餐品的日期
        List<String> alreadyDateList = baseDao.getAlreadyOrderDate(params);
        // 获取该餐品类别的提前订餐小时数和开始用餐时间
        BmsMealTypeDTO mealTypeDTO = baseDao.getAdvanceAndBeginTime(mealTypeId);
        Integer advanceTime = mealTypeDTO.getAdvanceOrderTime();
        String beginTime = mealTypeDTO.getBeginTime();
        // 定义返回的集合
        List<BmsEatDateDTO> eatDateDTOList = new ArrayList<>();
        // 循环，得出可选的日期区间信息（yyyy-MM-dd以及星期几)
        for (int i = beginNumber; i < endNumber; i++) {
            BmsEatDateDTO eatDateDTO = new BmsEatDateDTO();
            DateTime currentDate = beginDate.plusDays(i);
            String eatDate = currentDate.toString("yyyy-MM-dd");    //日期
            String dayOfWeek = currentDate.dayOfWeek().getAsShortText(Locale.CHINESE);  //星期几Locale.CHINESE

            String contrastDateStr = eatDate.concat(" ").concat(beginTime).concat(":00");
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            DateTime contrastDate = DateTime.parse(contrastDateStr, formatter);

            // 不可选的日期：已过订餐时限的餐品，或未过订餐时限但已订的餐品
            // 可选的：未到时限且未订
            if (eatDateDTOS != null) {
                if (dateTime.plusHours(advanceTime).isBefore(contrastDate) && !alreadyDateList.contains(eatDate) && !eatDateDTOS.contains(eatDate)) {
                    eatDateDTO.setDate(eatDate);
                    eatDateDTO.setDayOfWeek(dayOfWeek);
                    eatDateDTO.setChecked(false);
                    eatDateDTOList.add(eatDateDTO);
                }
            } else {
                if (dateTime.plusHours(advanceTime).isBefore(contrastDate) && !alreadyDateList.contains(eatDate)) {
                    eatDateDTO.setDate(eatDate);
                    eatDateDTO.setDayOfWeek(dayOfWeek);
                    eatDateDTO.setChecked(false);
                    eatDateDTOList.add(eatDateDTO);
                }
            }
        }
        // 得到可选的日期集合
        if (eatDateDTOList.size() != 0) {
            List<BmsEatDateDTO> result = new ArrayList<>();
            DateTime currentDateTime = new DateTime();
            int currentDay = currentDateTime.getDayOfWeek();
            QueryWrapper<SysParamsEntity> queryParams = new QueryWrapper<>();
            queryParams.eq("param_code", BmsConstant.STOP_ORDER_CODE);
            SysParamsEntity paramsEntity = paramsDao.selectOne(queryParams);
            if (paramsEntity != null && BmsConstant.STOP_ORDER_VALUE_ON.equals(paramsEntity.getParamValue())) {
                if (currentDay == 5 && Integer.parseInt(currentDateTime.toString("HH")) >= 11) {
                    int weekOfWeekyear = currentDateTime.getWeekOfWeekyear();
                    for (BmsEatDateDTO eatDateDTO : eatDateDTOList) {
                        String dayOfWeek = eatDateDTO.getDayOfWeek();
                        if (!(("星期六".equals(dayOfWeek) || "星期日".equals(dayOfWeek)) && new DateTime(eatDateDTO.getDate()).getWeekOfWeekyear() == weekOfWeekyear)) {
                            result.add(eatDateDTO);
                        }
                    }
                } else {
                    result.addAll(eatDateDTOList);
                }
            } else {
                result.addAll(eatDateDTOList);
            }
            return result;
        }
        return null;
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

}
