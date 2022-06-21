package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationTreeDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationUserDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsEvaluationUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 评价用户关联表 
 */
public interface BmsEvaluationUserService extends CrudService<BmsEvaluationUserEntity, BmsEvaluationUserDTO> {

    Result<PageData<BmsEvaluationUserDTO>> getPage(Map<String, Object> params);

    BmsEvaluationUserDTO selectEvaluationDetail(Long id);

    List<BmsEvaluationTreeDTO> getEvaluationStatistics(Long id);
}