package cn.amigosoft.modules.visit.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 人员报备申请表 
 */
@Mapper
public interface BmsVisitApplyDao extends BaseDao<BmsVisitApplyEntity> {

    //申请人获取报备记录
    List<BmsVisitApplyDTO> queryPage(Map<String, Object> params);

    //申请人获取草稿记录
    List<BmsVisitApplyDTO> queryDraftPage(Map<String, Object> params);

    //审批人获取报备记录
    List<BmsVisitApplyDTO> queryVerifyPage(Map<String, Object> params);

    //安保人员获取报备记录
    List<BmsVisitApplyDTO> queryAppliedPage(Map<String, Object> params);

    //获取申请详情
    BmsVisitApplyDTO getDetail(@Param("id") Long id);

    //获取申请详情新
    BmsVisitApplyDTO getDetailNew(@Param("id") Long id);
}