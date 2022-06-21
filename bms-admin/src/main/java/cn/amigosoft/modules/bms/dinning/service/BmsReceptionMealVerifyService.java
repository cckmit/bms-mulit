package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealVerifyEntity;

/**
 * 接待餐审批表
 */
public interface BmsReceptionMealVerifyService extends CrudService<BmsReceptionMealVerifyEntity, BmsReceptionMealVerifyDTO> {

    Result insertReceptionMealVerify(BmsReceptionMealVerifyDTO dto);
}