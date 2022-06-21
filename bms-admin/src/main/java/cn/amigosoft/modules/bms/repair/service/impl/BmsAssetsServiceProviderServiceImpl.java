package cn.amigosoft.modules.bms.repair.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.repair.dao.BmsAssetsServiceProviderDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsServiceProviderDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsServiceProviderEntity;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsServiceProviderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 服务商表
 */
@Service
public class BmsAssetsServiceProviderServiceImpl extends CrudServiceImpl<BmsAssetsServiceProviderDao, BmsAssetsServiceProviderEntity, BmsAssetsServiceProviderDTO> implements BmsAssetsServiceProviderService {

    @Override
    public QueryWrapper<BmsAssetsServiceProviderEntity> getWrapper(Map<String, Object> params) {

        QueryWrapper<BmsAssetsServiceProviderEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public Result<PageData<BmsAssetsServiceProviderDTO>> getPage(Map<String, Object> params) {
        IPage<BmsAssetsServiceProviderEntity> page = getPage(params, "p.create_date", false);
        List<BmsAssetsServiceProviderDTO> list = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsAssetsServiceProviderDTO>>().ok(getPageData(list, page.getTotal(), BmsAssetsServiceProviderDTO.class));
    }

    @Override
    public List<BmsAssetsServiceProviderDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public BmsAssetsServiceProviderDTO selectAssetsServiceProviderById(Long id) {
        return baseDao.selectAssetsServiceProviderById(id);
    }
}