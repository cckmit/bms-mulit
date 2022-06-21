package cn.amigosoft.modules.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVisitorDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVisitorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 人员报备访客关联表 
 */
@Mapper
public interface BmsVisitApplyVisitorDao extends BaseDao<BmsVisitApplyVisitorEntity> {

    //获取访客列表
    List<BmsVisitApplyVisitorDTO> getVisitorList(@Param("applyId") Long applyId);

    //获取历史访客列表
    List<BmsVisitApplyVisitorDTO> getVisitorHistoryList(@Param("creator") Long creator);

    //获取访客信息
    BmsVisitApplyVisitorDTO getVisitorInfo(Map<String, Object> params);
}