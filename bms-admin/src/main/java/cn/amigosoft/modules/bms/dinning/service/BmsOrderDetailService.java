package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsOrderDetailDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsOrderDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表 
 */
public interface BmsOrderDetailService extends CrudService<BmsOrderDetailEntity, BmsOrderDetailDTO> {

    Result<PageData<BmsOrderDetailDTO>> getPage(Map<String, Object> params);

    List<BmsOrderDetailDTO> selectExportList(Map<String, Object> params);

    int selectUserDetailCount(Map<String, Object> params);

    Result<PageData<BmsOrderDetailDTO>> getCountPage(Map<String, Object> params);

    List<BmsOrderDetailDTO> selectCountExportList(Map<String, Object> params);
}