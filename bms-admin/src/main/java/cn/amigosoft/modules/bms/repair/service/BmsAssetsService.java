package cn.amigosoft.modules.bms.repair.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsEntity;

import java.util.List;
import java.util.Map;

/**
 * 资产表 
 */
public interface BmsAssetsService extends CrudService<BmsAssetsEntity, BmsAssetsDTO> {

    Result<PageData<BmsAssetsDTO>> getPage(Map<String, Object> params);

    List<BmsAssetsDTO> selectExportList(Map<String, Object> params);

    BmsAssetsDTO selectAssetsById(Long id);

}