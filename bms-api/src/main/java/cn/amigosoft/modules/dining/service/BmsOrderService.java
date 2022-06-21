package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsEatDateDTO;
import cn.amigosoft.modules.dining.dto.BmsMealTypeDTO;
import cn.amigosoft.modules.dining.dto.BmsOrderDTO;
import cn.amigosoft.modules.dining.entity.BmsOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
public interface BmsOrderService extends CrudService<BmsOrderEntity, BmsOrderDTO> {

    //报餐记录列表查询
    PageData<BmsOrderDTO> queryPage(Map<String, Object> params);

    //获取报餐订单详情信息
    BmsOrderDTO getBaseInfo(Long id);

    //删除报餐订单
    Result delete(Long id);

    //新增报餐订单
    Result insertOrder(BmsOrderDTO dto);

    //获取报餐可选日期集合
    List<BmsEatDateDTO> chooseEatDates(BmsMealTypeDTO dto);

}