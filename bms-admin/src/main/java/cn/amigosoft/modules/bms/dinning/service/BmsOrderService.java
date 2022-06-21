package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 */
public interface BmsOrderService extends CrudService<BmsOrderEntity, BmsOrderDTO> {

    Result<PageData<BmsOrderDTO>> getPage(Map<String, Object> params);

    Result insertOrder(BmsOrderDTO dto);

    BmsOrderDTO selectOrderById(Long id);

    Result cancel(List<BmsOrderDetailDTO> list);

    List<BmsOrderDTO> selectExportList(Map<String, Object> params);

    Result deleteOrderByIds(Long[] ids);
}