package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsEvaluationEntity;

import java.util.List;
import java.util.Map;

/**
 * 评价表 
 */
public interface BmsEvaluationService extends CrudService<BmsEvaluationEntity, BmsEvaluationDTO> {

    Result<PageData<BmsEvaluationDTO>> getPage(Map<String, Object> params);

    BmsEvaluationDTO selectEvaluationById(Long id);

    List<BmsEvaluationDTO> selectExportList(Map<String, Object> params);

    void deleteEvaluationByIds(Long[] ids);

}