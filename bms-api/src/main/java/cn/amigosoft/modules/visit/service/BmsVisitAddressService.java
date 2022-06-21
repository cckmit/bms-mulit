package cn.amigosoft.modules.visit.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitAddressEntity;

import java.util.List;

/**
 * 访问地点表 
 */
public interface BmsVisitAddressService extends CrudService<BmsVisitAddressEntity, BmsVisitAddressDTO> {

    List<BmsVisitAddressDTO> getChooseList();
}