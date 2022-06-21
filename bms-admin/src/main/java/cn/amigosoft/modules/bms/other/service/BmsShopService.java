package cn.amigosoft.modules.bms.other.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.other.dto.BmsShopDTO;
import cn.amigosoft.modules.bms.other.entity.BmsShopEntity;

import java.util.List;
import java.util.Map;

/**
 * 店铺表 
 */
public interface BmsShopService extends CrudService<BmsShopEntity, BmsShopDTO> {

    Result<PageData<BmsShopDTO>> getPage(Map<String, Object> params);

    List<BmsShopDTO> selectExportList(Map<String, Object> params);

    BmsShopDTO selectShopById(Long id);
}