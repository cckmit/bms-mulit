package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsItemDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 餐品表 
 */
public interface BmsItemService extends CrudService<BmsItemEntity, BmsItemDTO> {

    Result<PageData<BmsItemDTO>> getPage(Map<String, Object> params);

    BmsItemDTO selectItemById(Long id);

    List<BmsItemDTO> selectExportList(Map<String, Object> params);

    /* 餐品搜索框列表 */
    List<BmsItemDTO> getItemList();

}