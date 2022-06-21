package cn.amigosoft.modules.visit.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVerifyEntity;

/**
 * 人员报备审批表 
 */
public interface BmsVisitApplyVerifyService extends CrudService<BmsVisitApplyVerifyEntity, BmsVisitApplyVerifyDTO> {

    Result saveVerify(BmsVisitApplyVerifyDTO dto);

}