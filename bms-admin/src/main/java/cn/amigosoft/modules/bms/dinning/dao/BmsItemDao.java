package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsItemDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsItemEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 餐品表
 */
@Mapper
public interface BmsItemDao extends BaseDao<BmsItemEntity> {

    List<BmsItemDTO> selectPageList(@Param("page") IPage<BmsItemEntity> page, @Param("params") Map<String, Object> params);

    BmsItemDTO selectItemById(Long id);

    List<BmsItemDTO> selectExportList(Map<String, Object> params);

    /* 餐品搜索框列表 */
    List<BmsItemDTO> getItemList();

}