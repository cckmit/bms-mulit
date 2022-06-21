package cn.amigosoft.modules.bms.other.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.other.dto.BmsGoodsDTO;
import cn.amigosoft.modules.bms.other.entity.BmsGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 货品表
 */
public interface BmsGoodsService extends CrudService<BmsGoodsEntity, BmsGoodsDTO> {

    Result<PageData<BmsGoodsDTO>> getPage(Map<String, Object> params);

    List<BmsGoodsDTO> selectExportList(Map<String, Object> params);

    BmsGoodsDTO selectGoodsById(Long id);

}