package cn.amigosoft.modules.assets.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairRecordEntity;
import cn.amigosoft.modules.sys.dto.SysUserDTO;

import java.util.List;
import java.util.Map;

/**
 * 资产维修记录表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
public interface BmsAssetsRepairRecordService extends CrudService<BmsAssetsRepairRecordEntity, BmsAssetsRepairRecordDTO> {

    //报修审批
    Result addVerifyRecord(BmsAssetsRepairRecordDTO dto);

    //报修处理
    Result addDealRecord(BmsAssetsRepairRecordDTO dto);

    // 获取内部维修人员列表
    List<SysUserDTO> getRepairServiceList(Map<String, Object> params);

    // 获取外部维修人员列表
    List<SysUserDTO> getExternalServiceList(Map<String, Object> params);


}