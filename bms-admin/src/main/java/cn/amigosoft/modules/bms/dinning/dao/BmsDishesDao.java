package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsDishesDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsDishesEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜品表
 */
@Mapper
public interface BmsDishesDao extends BaseDao<BmsDishesEntity> {

    List<BmsDishesDTO> selectPageList(@Param("page") IPage<BmsDishesEntity> page, @Param("params") Map<String, Object> params);

    BmsDishesDTO selectDishes(Long id);

    List<BmsDishesDTO> export(Map<String, Object> params);

    List<BmsDishesDTO> selectBaseDishesInfo(BmsDishesDTO dto);

}