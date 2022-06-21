package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsEvaluationEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评价表
 */
@Mapper
public interface BmsEvaluationDao extends BaseDao<BmsEvaluationEntity> {

    List<BmsEvaluationDTO> selectPageList(@Param("page") IPage<BmsEvaluationEntity> page, @Param("params") Map<String, Object> params);

    List<BmsEvaluationDTO> selectExportList(Map<String, Object> params);

    BmsEvaluationDTO selectEvaluationById(Long id);

    int deleteEvaluationByIds(Long[] ids);

}