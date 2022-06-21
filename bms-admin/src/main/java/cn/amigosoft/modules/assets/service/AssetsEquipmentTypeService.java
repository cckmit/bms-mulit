package cn.amigosoft.modules.assets.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentTypeDTO;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentTypeTreeDTO;
import cn.amigosoft.modules.assets.entity.AssetsEquipmentTypeEntity;

import java.util.List;

/**
 * <p>
 * 资产设备分类表  服务接口类
 * </p>
 *
 * @author : cxb
 * @version : 1.0
 * @date : 2021-05-31 15:29:44
 */
public interface AssetsEquipmentTypeService extends CrudService<AssetsEquipmentTypeEntity, AssetsEquipmentTypeDTO> {

    Result saveEquipmentType(AssetsEquipmentTypeDTO dto);

    Result saveEquipmentTypeCheck(AssetsEquipmentTypeDTO dto, AssetsEquipmentTypeEntity assetsEquipmentTypePid);

    Result updateEquipmentType(AssetsEquipmentTypeDTO dto);

    Result updateEquipmentTypeCheck(AssetsEquipmentTypeDTO dto, AssetsEquipmentTypeEntity assetsEquipmentTypePid);

    Result deleteEquipmentType(Long id);

    List<AssetsEquipmentTypeTreeDTO> pageList();

    List<AssetsEquipmentTypeTreeDTO> listTreeSelect();

    List<AssetsEquipmentTypeTreeDTO> allTypeTree();

    List<AssetsEquipmentTypeTreeDTO> universalTypeTree();
}

