package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsReceptionMealDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsReceptionMealEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 接待餐表
 */
@Mapper
public interface BmsReceptionMealDao extends BaseDao<BmsReceptionMealEntity> {

    List<BmsReceptionMealDTO> selectPageList(@Param("page") IPage<BmsReceptionMealEntity> page, @Param("params") Map<String, Object> params);

    BmsReceptionMealDTO selectReceptionDetail(Long id);

    List<BmsReceptionMealDTO> selectExportList(Map<String, Object> params);

    List<BmsReceptionMealDTO> selectCountPageList(@Param("page") IPage<BmsReceptionMealEntity> page, @Param("params") Map<String, Object> params);

    List<BmsReceptionMealDTO> selectCountExportList(Map<String, Object> params);

}