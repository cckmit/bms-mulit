package cn.amigosoft.modules.bms.repair.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsServiceProviderEntity;

import java.util.List;
import java.util.Map;

/**
 * 服务商表 
 */
public interface BmsAssetsServiceProviderService extends CrudService<BmsAssetsServiceProviderEntity, BmsAssetsServiceProviderDTO> {

    Result<PageData<BmsAssetsServiceProviderDTO>> getPage(Map<String, Object> params);

    List<BmsAssetsServiceProviderDTO> selectExportList(Map<String, Object> params);

    BmsAssetsServiceProviderDTO selectAssetsServiceProviderById(Long id);

}