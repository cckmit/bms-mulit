package cn.amigosoft.modules.bms.other.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.other.dao.BmsShopDao;
import cn.amigosoft.modules.bms.other.dto.BmsShopDTO;
import cn.amigosoft.modules.bms.other.entity.BmsShopEntity;
import cn.amigosoft.modules.bms.other.service.BmsShopService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 店铺表
 */
@Service
public class BmsShopServiceImpl extends CrudServiceImpl<BmsShopDao, BmsShopEntity, BmsShopDTO> implements BmsShopService {

    @Override
    public QueryWrapper<BmsShopEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsShopEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }


    @Override
    public Result<PageData<BmsShopDTO>> getPage(Map<String, Object> params) {
        IPage<BmsShopEntity> page = getPage(params, "s.create_date", false);
        List<BmsShopDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsShopDTO>>().ok(getPageData(resultList, page.getTotal(), BmsShopDTO.class));
    }

    @Override
    public List<BmsShopDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public BmsShopDTO selectShopById(Long id) {
        return baseDao.selectShopById(id);
    }

}