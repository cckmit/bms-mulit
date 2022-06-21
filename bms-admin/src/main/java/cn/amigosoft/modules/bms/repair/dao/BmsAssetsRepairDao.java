package cn.amigosoft.modules.bms.repair.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产维修表
 */
@Mapper
public interface BmsAssetsRepairDao extends BaseDao<BmsAssetsRepairEntity> {

    List<BmsAssetsRepairDTO> selectPageList(@Param("page") IPage<BmsAssetsRepairEntity> page, @Param("params") Map<String, Object> params);

    BmsAssetsRepairDTO selectAssetsRepairById(Long id);

    List<BmsAssetsRepairDTO> selectExportList(Map<String, Object> params);

}