package cn.amigosoft.modules.visit.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.visit.dto.BmsVisitApplyDTO;
import cn.amigosoft.modules.visit.entity.BmsVisitApplyEntity;
import cn.amigosoft.modules.sys.dto.SysUserDTO;

import java.util.List;
import java.util.Map;

/**
 * 人员报备申请表 
 */
public interface BmsVisitApplyService extends CrudService<BmsVisitApplyEntity, BmsVisitApplyDTO> {

    // 申请人获取报备记录
    PageData<BmsVisitApplyDTO> queryPage(Map<String, Object> params);

    // 申请人获取草稿记录
    PageData<BmsVisitApplyDTO> queryDraftPage(Map<String, Object> params);

    // 审批人获取报备记录
    PageData<BmsVisitApplyDTO> queryVerifyPage(Map<String, Object> params);

    // 安保人员获取报备记录
    PageData<BmsVisitApplyDTO> queryAppliedPage(Map<String, Object> params);

    // 获取报备详情
    BmsVisitApplyDTO getDetail(Long id);

    // 获取报备草稿详情
    BmsVisitApplyDTO getDraftDetail(Long id);

    // 新增访客报备申请
    Result addVisitApply(BmsVisitApplyDTO dto);

    // 新增访客报备登记
    Result addVisitRegister(BmsVisitApplyDTO dto);

    // 提交访客报备登记
    Result approveRegister(BmsVisitApplyDTO dto);

    // 删除访客报备登记
    Result deleteVisitRegister(Long id);

    // 新增访客报备草稿
    Result addVisitDraft(BmsVisitApplyDTO dto);

    // 删除访客报备草稿
    Result deleteVisitDraft(Long id);

    // 获取主管审核人员列表
    List<SysUserDTO> getVerifyList();

    // 获取保安审核人员列表
    List<SysUserDTO> getGuardVerifyList();
}