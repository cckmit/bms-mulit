package cn.amigosoft.modules.assets.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.assets.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsServiceProviderEntity;

import java.util.Map;

/**
 * 服务商表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
public interface BmsAssetsServiceProviderService extends CrudService<BmsAssetsServiceProviderEntity, BmsAssetsServiceProviderDTO> {

    // 获取服务商列表
    PageData<BmsAssetsServiceProviderDTO> queryPage(Map<String, Object> params);

    // 服务商详情
    BmsAssetsServiceProviderDTO getDetail(Long id);
}