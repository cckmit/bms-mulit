package cn.amigosoft.modules.assets.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairEntity;
import cn.amigosoft.modules.sys.dto.SysUserDTO;

import java.util.List;
import java.util.Map;

/**
 * 资产维修表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
public interface BmsAssetsRepairService extends CrudService<BmsAssetsRepairEntity, BmsAssetsRepairDTO> {

    /* 申请人获取申请列表 */
    PageData<BmsAssetsRepairDTO> queryPage(Map<String, Object> params);

    /* 审批人员获取审批记录 */
    PageData<BmsAssetsRepairDTO> queryVerifyPage(Map<String, Object> params);

    /* 物业获取维修管理（处理）记录 */
    PageData<BmsAssetsRepairDTO> queryDealPage(Map<String, Object> params);

    /* 维修人员获取维修记录 */
    PageData<BmsAssetsRepairDTO> queryServicePage(Map<String, Object> params);


    /* 获取申请详情 */
    BmsAssetsRepairDTO getDetail(Long id);

    /* 维修人员获取维修详情 */
    BmsAssetsRepairDTO getServiceDetail(Long id);

    /* 新增报修申请 */
    void addRepairApply(BmsAssetsRepairDTO dto);

    /* 报修结果提交 */
    Result serviceResult(BmsAssetsRepairDTO dto);

    /* 报修评价 */
    Result evaluation(BmsAssetsRepairDTO dto);

    /* 获取审核人员列表 */
    List<SysUserDTO> getVerifyList();
}