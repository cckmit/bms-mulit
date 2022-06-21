package cn.amigosoft.modules.bms.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.visit.dto.BmsVisitApplyVerifyDTO;
import cn.amigosoft.modules.bms.visit.entity.BmsVisitApplyVerifyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人员报备审批表 
 */
@Mapper
public interface BmsVisitApplyVerifyDao extends BaseDao<BmsVisitApplyVerifyEntity> {

    List<BmsVisitApplyVerifyDTO> selectVerifyListByApplyId(Long id);

}