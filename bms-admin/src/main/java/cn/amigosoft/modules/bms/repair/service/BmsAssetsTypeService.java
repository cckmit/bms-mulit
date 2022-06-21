package cn.amigosoft.modules.bms.repair.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsTypeDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 资产类别表 
 */
public interface BmsAssetsTypeService extends CrudService<BmsAssetsTypeEntity, BmsAssetsTypeDTO> {

    List<BmsAssetsTypeDTO> selectAssetsTypeList();

    List<BmsAssetsTypeDTO> selectExportList(Map<String, Object> params);

}