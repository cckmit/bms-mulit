package cn.amigosoft.modules.visit.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyVisitorDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyVisitorEntity;

import java.util.List;
import java.util.Map;

/**
 * 人员报备访客关联表 
 */
public interface BmsVisitApplyVisitorService extends CrudService<BmsVisitApplyVisitorEntity, BmsVisitApplyVisitorDTO> {

    // 获取历史访客列表
    List<BmsVisitApplyVisitorDTO> getVisitorHistoryList();

    // 获取访客信息
    BmsVisitApplyVisitorDTO getVisitorInfo(Map<String, Object> params);
}