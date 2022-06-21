package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDetailInfoDTO;
import cn.amigosoft.modules.dining.entity.BmsOrderDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
public interface BmsOrderDetailService extends CrudService<BmsOrderDetailEntity, BmsOrderDetailDTO> {

    //获取报餐订单的详情（餐次集合）
    List<BmsOrderDetailInfoDTO> getDetailInfo(Long id);

    //获取报餐订单对应的餐次状态与时间信息
    List<BmsOrderDetailDTO> getOrderStatusList(Long id);

    //删除订单详情中的某一餐
    Result delete(Long id);

    //核销列表分页查询
    PageData<BmsOrderDetailDTO> paidPage(Map<String, Object> params);

    //扫码核销记录列表分页查询
    PageData<BmsOrderDetailDTO> scanPaidPage(Map<String, Object> params);

    //核销详情信息查询
    BmsOrderDetailDTO getPaidDetailInfo(Long id);

    //扫码核销详情信息
    BmsOrderDetailDTO getScanDetailInfo(Map<String, Object> params);

    //报餐人数统计分页查询
    PageData<BmsOrderDetailDTO> countPage(Map<String, Object> params);

    List<Long> onOrderItemIds();

    //获取用户上一个订单中最新的报餐食堂
    BmsOrderDetailDTO lastCanteen();
}