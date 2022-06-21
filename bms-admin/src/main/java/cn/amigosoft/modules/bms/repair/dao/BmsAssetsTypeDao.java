package cn.amigosoft.modules.bms.repair.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsTypeDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 资产类别表 
 */
@Mapper
public interface BmsAssetsTypeDao extends BaseDao<BmsAssetsTypeEntity> {

    List<BmsAssetsTypeDTO> selectExportList(Map<String, Object> params);

}