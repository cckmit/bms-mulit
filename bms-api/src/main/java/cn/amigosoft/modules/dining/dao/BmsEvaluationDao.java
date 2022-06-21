package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.dining.entity.BmsEvaluationEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评价表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-18
 */
@Mapper
public interface BmsEvaluationDao extends BaseDao<BmsEvaluationEntity> {

    List<BmsEvaluationDTO> selectPageList(@Param("page") IPage<BmsEvaluationEntity> page, @Param("params") Map<String, Object> params);

    /**
     * 评论详情
     */
    BmsEvaluationDTO getDetail(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * 评价
     */
    void evaluate(Map<String, Object> params);

}