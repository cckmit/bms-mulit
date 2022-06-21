package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsItemDishesDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsItemDishesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 餐品菜品关联表
 */
@Mapper
public interface BmsItemDishesDao extends BaseDao<BmsItemDishesEntity> {

    List<BmsItemDishesDTO> selectItemDishesByItemId(Long itemId);

    List<String> selectIdByItemId(Long id);

    int deleteByItemIdAndDishesId(@Param("itemId") Long itemId, @Param("dishesId") String dishesId);

}