package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailInfoDTO;
import cn.amigosoft.modules.dining.entity.BmsOrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Mapper
public interface BmsOrderDetailDao extends BaseDao<BmsOrderDetailEntity> {

    /*//获取报餐订单详情
    List<BmsOrderDetailInfoDTO> getDetailInfo(@Param("id") Long id);*/

    //获取报餐订单对应的餐次状态与时间信息
    List<BmsOrderDetailDTO> getOrderStatusList(@Param("order_id") Long orderId);

    //获取某一餐次的状态与时间信息
    BmsOrderDetailDTO getOrderDetailStatus(@Param("id") Long id);

    //获取报订单中的日期集合
    List<BmsOrderDetailInfoDTO> getOrderDateList(@Param("order_id") Long orderId);

    //根据订单ID和用餐日期获取餐次集合
    List<BmsOrderDetailDTO> getOrderDetailList(@Param("order_id") Long orderId, @Param("eat_date") String eatDate);

    //核销列表条件查询
    List<BmsOrderDetailDTO> paidPage(Map<String, Object> params);

    //扫码核销记录列表条件查询
    List<BmsOrderDetailDTO> scanPaidPage(Map<String, Object> params);

    //核销详情信息
    BmsOrderDetailDTO getPaidDetailInfo(@Param("id") Long id);

    //扫码核销详情信息
    BmsOrderDetailDTO getScanDetailInfo(Map<String, Object> params);

    //报餐人数统计分页查询
    List<BmsOrderDetailDTO> countPage(Map<String, Object> params);

    List<Long> onOrderItemIds(Map<String, Object> params);

    //获取用户上一个订单中最新的报餐食堂
    BmsOrderDetailDTO lastCanteen(@Param("creator") Long creator);

    int selectUserDetailCount(@Param("creator") Long creator, @Param("eatDate") String eatDate, @Param("mealTypeId") Long mealTypeId);

    //获取用户当月取消用餐的记录
    List<BmsOrderDetailDTO> selectMonthCancelList(Long creator);
}