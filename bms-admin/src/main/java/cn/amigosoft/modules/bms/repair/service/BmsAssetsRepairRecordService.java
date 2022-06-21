package cn.amigosoft.modules.bms.repair.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairRecordEntity;

/**
 * 资产维修记录表 
 */
public interface BmsAssetsRepairRecordService extends CrudService<BmsAssetsRepairRecordEntity, BmsAssetsRepairRecordDTO> {

    Result insertAssetsRepairRecord(BmsAssetsRepairRecordDTO dto);

}