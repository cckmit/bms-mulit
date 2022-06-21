package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.BmsItemDTO;
import cn.amigosoft.modules.dining.entity.BmsItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 餐品表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Mapper
public interface BmsItemDao extends BaseDao<BmsItemEntity> {

    List<BmsItemDTO> selectItemList(Map<String, Object> params);

    List<String> selectDishesImage(@Param("itemId") Long id);

    BmsItemDTO selectItemById(Long id);

    List<BmsItemDTO> selectItemByIds(Long[] ids);

    /* 餐品搜索框列表 */
    List<BmsItemDTO> getItemList();
}