package cn.amigosoft.modules.bms.visit.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 人员报备申请表 
 */
public interface BmsVisitApplyService extends CrudService<BmsVisitApplyEntity, BmsVisitApplyDTO> {

    Result<PageData<BmsVisitApplyDTO>> getApplyPage(Map<String, Object> params);

    Result<PageData<BmsVisitApplyDTO>> getPage(Map<String, Object> params);

    Result<PageData<BmsVisitApplyDTO>> getApprovePage(Map<String, Object> params);

    BmsVisitApplyDTO selectVisitApplyById(Long id);

    List<BmsVisitApplyDTO> selectExportList(Map<String, Object> params);

    Result insertVisitApply(BmsVisitApplyDTO dto);

    List<BmsVisitApplyDTO> selectVerifyExportList(Map<String, Object> params);

    List<BmsVisitApplyDTO> selectRecordExportList(Map<String, Object> params);
}