package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.dining.dto.BmsItemDTO;
import cn.amigosoft.modules.dining.entity.BmsItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 餐品表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
public interface BmsItemService extends CrudService<BmsItemEntity, BmsItemDTO> {

    List<BmsItemDTO> selectItemList(Map<String, Object> params);

    BmsItemDTO selectItemById(Long id);

    List<BmsItemDTO> selectItemByIds(Long[] ids);

    /* 餐品搜索框列表 */
    List<BmsItemDTO> getItemList();

}