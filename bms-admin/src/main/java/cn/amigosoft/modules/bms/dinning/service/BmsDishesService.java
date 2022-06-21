package cn.amigosoft.modules.bms.dinning.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dto.BmsDishesDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsDishesEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜品表 
 */
public interface BmsDishesService extends CrudService<BmsDishesEntity, BmsDishesDTO> {

    Result<PageData<BmsDishesDTO>> getPage(Map<String, Object> params);

    BmsDishesDTO selectDishes(Long id);

    List<BmsDishesDTO> export(Map<String, Object> params);

    List<BmsDishesDTO> selectBaseDishesInfo(BmsDishesDTO dto);
}