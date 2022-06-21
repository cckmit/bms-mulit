package cn.amigosoft.modules.assets.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.assets.dto.AssetsEquipmentTypeTreeDTO;
import cn.amigosoft.modules.assets.entity.AssetsEquipmentTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资产设备分类表  Dao 接口
 * </p>
 *
 * @author : cxb
 * @version : 1.0
 * @date : 2021-05-31 15:29:44
 */
@Mapper
public interface AssetsEquipmentTypeDao extends BaseDao<AssetsEquipmentTypeEntity> {
    Integer countByTypeNo(@Param("typeNo") String typeNo);

    Integer countByPid(@Param("pid") Long pid);

    List<AssetsEquipmentTypeEntity> getByPid(@Param("pid") Long pid);

    List<AssetsEquipmentTypeTreeDTO> pageList(@Param("tenantId") Long tenantId, @Param("id") Long id);


    List<AssetsEquipmentTypeTreeDTO> listTreeSelect(@Param("tenantId") Long tenantId, @Param("id") Long id);

    AssetsEquipmentTypeEntity selectByPid(@Param("pid") Long pid);

    void updateDelByPid(@Param("id") Long id);

    List<AssetsEquipmentTypeTreeDTO> allTypeTree(@Param("tenantId") Long tenantId, @Param("id") Long id);

    List<AssetsEquipmentTypeTreeDTO> universalTypeTree(@Param("tenantId") Long tenantId, @Param("typeId") Long typeId);

    Integer countByNameAndId(@Param("name") String name, @Param("id") Long id, @Param("tenantId") Long tenantId);
}
