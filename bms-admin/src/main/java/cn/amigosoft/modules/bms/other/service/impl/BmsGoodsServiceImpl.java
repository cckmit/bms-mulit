package cn.amigosoft.modules.bms.other.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.other.dao.BmsGoodsDao;
import cn.amigosoft.modules.bms.other.dto.BmsGoodsDTO;
import cn.amigosoft.modules.bms.other.entity.BmsGoodsEntity;
import cn.amigosoft.modules.bms.other.service.BmsGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 货品表 
 */
@Service
public class BmsGoodsServiceImpl extends CrudServiceImpl<BmsGoodsDao, BmsGoodsEntity, BmsGoodsDTO> implements BmsGoodsService {

    @Override
    public QueryWrapper<BmsGoodsEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<BmsGoodsEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }


    @Override
    public Result<PageData<BmsGoodsDTO>> getPage(Map<String, Object> params) {
        IPage<BmsGoodsEntity> page = getPage(params, "g.create_date", false);
        List<BmsGoodsDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsGoodsDTO>>().ok(getPageData(resultList, page.getTotal(), BmsGoodsDTO.class));
    }


    @Override
    public List<BmsGoodsDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public BmsGoodsDTO selectGoodsById(Long id) {
        return baseDao.selectGoodsById(id);
    }
}