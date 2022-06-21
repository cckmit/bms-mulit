package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsReceptionMealVerifyDTO;
import cn.amigosoft.modules.dining.entity.BmsReceptionMealVerifyEntity;

/**
 * 接待餐审批表 
 */
public interface BmsReceptionMealVerifyService extends CrudService<BmsReceptionMealVerifyEntity, BmsReceptionMealVerifyDTO> {

    Result saveVerify(BmsReceptionMealVerifyDTO dto);
}