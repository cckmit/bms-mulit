package cn.amigosoft.modules.assets.service.impl;

import cn.amigosoft.common.page.PageData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.modules.assets.dao.BmsAssetsServiceProviderDao;
import cn.amigosoft.modules.assets.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsServiceProviderEntity;
import cn.amigosoft.modules.assets.service.BmsAssetsServiceProviderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 服务商表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Service
public class BmsAssetsServiceProviderServiceImpl extends CrudServiceImpl<BmsAssetsServiceProviderDao, BmsAssetsServiceProviderEntity, BmsAssetsServiceProviderDTO> implements BmsAssetsServiceProviderService {

    @Override
    public QueryWrapper<BmsAssetsServiceProviderEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BmsAssetsServiceProviderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<BmsAssetsServiceProviderDTO> queryPage(Map<String, Object> params) {
        IPage<BmsAssetsServiceProviderEntity> page = getPage(params, "a.create_date", false);
        List<BmsAssetsServiceProviderDTO> list = baseDao.queryPage(params);
        return getPageData(list, page.getTotal(), BmsAssetsServiceProviderDTO.class);
    }

    /* 详情 */
    @Override
    public BmsAssetsServiceProviderDTO getDetail(Long id) {
        BmsAssetsServiceProviderDTO providerDTO = baseDao.getDetail(id);
        return providerDTO;
    }
}