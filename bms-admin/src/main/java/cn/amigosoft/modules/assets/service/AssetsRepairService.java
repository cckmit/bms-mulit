package cn.amigosoft.modules.assets.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.assets.dto.AssetsRepairDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairDetailDTO;
import cn.amigosoft.modules.assets.dto.AssetsRepairPageListDTO;
import cn.amigosoft.modules.assets.entity.AssetsRepairEntity;

import java.util.Map;

/**
 * <p>
 *  资产维修表 服务接口类
 * </p>
 *
 * @author : hupsh
 * @version : 1.0
 * @date : 2021-05-31 11:00:31
 */
public interface AssetsRepairService extends CrudService<AssetsRepairEntity, AssetsRepairDTO> {
    //资产维修列表
    PageData<AssetsRepairPageListDTO> getAssetsRepairPageList(Map<String, Object> params);
    //资产维修详情
    AssetsRepairDetailDTO getAssetsRepairDetail(Long id);
    //处理维修工单(直接处理或者指派)
    Result dealAssetsRepair(AssetsRepairDTO assetsRepairDTO);
    //评价
    Result evaluateAssetsRepair(AssetsRepairDTO assetsRepairDTO);
}

