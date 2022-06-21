package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.dining.entity.BmsEvaluationEntity;

import java.util.Map;

/**
 * 评价表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-18
 */
public interface BmsEvaluationService extends CrudService<BmsEvaluationEntity, BmsEvaluationDTO> {

    //评价列表
    PageData<BmsEvaluationDTO> queryPage(Map<String, Object> params);

    //评价详情
    BmsEvaluationDTO getDetail(Long id);

    //评价
    Result evaluate(BmsEvaluationDTO evaluationDTO);
}