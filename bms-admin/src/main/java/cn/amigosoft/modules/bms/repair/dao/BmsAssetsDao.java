package cn.amigosoft.modules.bms.repair.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产表
 */
@Mapper
public interface BmsAssetsDao extends BaseDao<BmsAssetsEntity> {

    List<BmsAssetsDTO> selectPageList(@Param("page") IPage<BmsAssetsEntity> page, @Param("params") Map<String, Object> params);

    List<BmsAssetsDTO> selectExportList(Map<String, Object> params);

    BmsAssetsDTO selectAssetsById(Long id);
}