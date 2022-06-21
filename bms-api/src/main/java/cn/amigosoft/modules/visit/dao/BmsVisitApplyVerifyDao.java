package cn.amigosoft.modules.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVerifyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员报备审批表 
 */
@Mapper
public interface BmsVisitApplyVerifyDao extends BaseDao<BmsVisitApplyVerifyEntity> {

    List<BmsVisitApplyVerifyDTO> selectVerifyList(@Param("applyId") Long applyId);
}