package cn.amigosoft.modules.bms.other.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.bms.other.dto.BmsAreaDTO;
import cn.amigosoft.modules.bms.other.entity.BmsAreaEntity;

import java.util.List;

/**
 * 区域表 
 */
public interface BmsAreaService extends CrudService<BmsAreaEntity, BmsAreaDTO> {

    List<BmsAreaDTO> selectAreaList();

}