package cn.amigosoft.modules.bms.visit.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyVerifyEntity;

/**
 * 人员报备审批表 
 */
public interface BmsVisitApplyVerifyService extends CrudService<BmsVisitApplyVerifyEntity, BmsVisitApplyVerifyDTO> {

    Result insertVisitApplyVerify(BmsVisitApplyVerifyDTO dto);
}