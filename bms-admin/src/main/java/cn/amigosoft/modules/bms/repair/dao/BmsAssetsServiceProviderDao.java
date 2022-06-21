package cn.amigosoft.modules.bms.repair.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsServiceProviderEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 服务商表
 */
@Mapper
public interface BmsAssetsServiceProviderDao extends BaseDao<BmsAssetsServiceProviderEntity> {

    List<BmsAssetsServiceProviderDTO> selectPageList(@Param("page") IPage<BmsAssetsServiceProviderEntity> page, @Param("params") Map<String, Object> params);

    List<BmsAssetsServiceProviderDTO> selectExportList(Map<String, Object> params);

    BmsAssetsServiceProviderDTO selectAssetsServiceProviderById(Long id);

}