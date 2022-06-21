package cn.amigosoft.modules.bms.repair.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.repair.dao.BmsAssetsDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsEntity;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资产表
 */
@Service
public class BmsAssetsServiceImpl extends CrudServiceImpl<BmsAssetsDao, BmsAssetsEntity, BmsAssetsDTO> implements BmsAssetsService {

    @Override
    public QueryWrapper<BmsAssetsEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsAssetsEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }


    @Override
    public Result<PageData<BmsAssetsDTO>> getPage(Map<String, Object> params) {
        IPage<BmsAssetsEntity> page = getPage(params, "a.create_date", false);
        List<BmsAssetsDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsAssetsDTO>>().ok(getPageData(resultList, page.getTotal(), BmsAssetsDTO.class));
    }

    @Override
    public List<BmsAssetsDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public BmsAssetsDTO selectAssetsById(Long id) {
        return baseDao.selectAssetsById(id);
    }
}