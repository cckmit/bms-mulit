package cn.amigosoft.modules.bms.visit.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitAddressDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitAddressEntity;

/**
 * 访问地点表
 */
public interface BmsVisitAddressService extends CrudService<BmsVisitAddressEntity, BmsVisitAddressDTO> {

    BmsVisitAddressDTO selectAddressById(Long id);

    Result insertAddress(BmsVisitAddressDTO dto);

    Result updateAddress(BmsVisitAddressDTO dto);

}
