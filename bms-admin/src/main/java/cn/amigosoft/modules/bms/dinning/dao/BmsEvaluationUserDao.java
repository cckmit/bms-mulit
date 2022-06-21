package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsEvaluationUserDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsEvaluationUserEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评价用户关联表
 */
@Mapper
public interface BmsEvaluationUserDao extends BaseDao<BmsEvaluationUserEntity> {

    List<BmsEvaluationUserDTO> selectPageList(@Param("page") IPage<BmsEvaluationUserEntity> page, @Param("params") Map<String, Object> params);

    BmsEvaluationUserDTO selectEvaluationDetail(Long id);

    List<String> selectUserIdByEvaluationId(Long evaluationId);

    int deleteByEvaluationIdAndUserId(@Param("evaluationId") Long evaluationId, @Param("userId") String userId);

    int deleteByEvaluationIds(Long[] evaluationIds);

    List<String> selectContentByEvaluationId(Long evaluationId);
}