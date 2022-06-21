package cn.amigosoft.modules.bms.repair.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairEntity;

import java.util.List;
import java.util.Map;

/**
 * 资产维修表 
 */
public interface BmsAssetsRepairService extends CrudService<BmsAssetsRepairEntity, BmsAssetsRepairDTO> {

    Result<PageData<BmsAssetsRepairDTO>> getPage(Map<String, Object> params);

    Result<PageData<BmsAssetsRepairDTO>> getVerifyPage(Map<String, Object> params);

    Result<PageData<BmsAssetsRepairDTO>> getRecordPage(Map<String, Object> params);

    Result<PageData<BmsAssetsRepairDTO>> getStatisticsPage(Map<String, Object> params);

    BmsAssetsRepairDTO selectAssetsRepairById(Long id);

    void insertAssetsRepair(BmsAssetsRepairDTO dto);

    List<BmsAssetsRepairDTO> selectApplyExportList(Map<String, Object> params);

    List<BmsAssetsRepairDTO> selectVerifyExportList(Map<String, Object> params);

    List<BmsAssetsRepairDTO> selectRecordExportList(Map<String, Object> params);

    List<BmsAssetsRepairDTO> selectStatisticsExportList(Map<String, Object> params);

}