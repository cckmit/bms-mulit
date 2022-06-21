package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealEntity;

import java.util.List;
import java.util.Map;

/**
 * 接待餐表
 */
public interface BmsReceptionMealService extends CrudService<BmsReceptionMealEntity, BmsReceptionMealDTO> {

    Result<PageData<BmsReceptionMealDTO>> getPage(Map<String, Object> params);

    Result<PageData<BmsReceptionMealDTO>> getVerifyPage(Map<String, Object> params);

    BmsReceptionMealDTO selectReceptionDetail(Long id);

    Result saveReceptionMeal(BmsReceptionMealDTO dto);

    List<BmsReceptionMealDTO> selectApplyExportList(Map<String, Object> params);

    List<BmsReceptionMealDTO> selectVerifyExportList(Map<String, Object> params);

    List<BmsReceptionMealDTO> selectStatisticsExport(Map<String, Object> params);

    Result<PageData<BmsReceptionMealDTO>> getStatisticsPage(Map<String, Object> params);

    Result<PageData<BmsReceptionMealDTO>> getCountPage(Map<String, Object> params);

    List<BmsReceptionMealDTO> selectCountExport(Map<String, Object> params);
}