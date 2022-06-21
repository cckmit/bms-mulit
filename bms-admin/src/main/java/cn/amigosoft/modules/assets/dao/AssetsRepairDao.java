package cn.amigosoft.modules.assets.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.assets.dto.AssetsRepairDetailDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairPageListDTO;
import cn.amigosoft.modules.assets.entity.AssetsRepairEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产维修表 Dao 接口
 * </p>
 *
 * @author : hupsh
 * @version : 1.0
 * @date : 2021-05-31 11:00:31
 */
@Mapper
public interface AssetsRepairDao extends BaseDao<AssetsRepairEntity> {
    //维修列表
    List<AssetsRepairPageListDTO> getAssetsRepairPageList(Page page, @Param("ew") QueryWrapper<AssetsRepairPageListDTO> queryWrapper);

    //查询详情
    AssetsRepairDetailDTO getAssetsRepairDetail(@Param("id") Long id);

    //处理资产维修
    void dealAssetsRepair(@Param("params") Map<String, Object> params);

    //评价
    void evaluateAssetsRepair(@Param("params") Map<String, Object> params);
}
