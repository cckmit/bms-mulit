package cn.amigosoft.modules.assets.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.assets.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsServiceProviderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 服务商表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Mapper
public interface BmsAssetsServiceProviderDao extends BaseDao<BmsAssetsServiceProviderEntity> {

    List<BmsAssetsServiceProviderDTO> queryPage(Map<String, Object> params);

    BmsAssetsServiceProviderDTO getDetail(@Param("id") Long id);
}