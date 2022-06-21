package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.dinning.dao.BmsDishesDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsDishesDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsDishesEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsDishesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜品表
 */
@Service
public class BmsDishesServiceImpl extends CrudServiceImpl<BmsDishesDao, BmsDishesEntity, BmsDishesDTO> implements BmsDishesService {

    @Override
    public QueryWrapper<BmsDishesEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");
        String canteenId = (String) params.get("canteenId");
        String typeId = (String) params.get("typeId");

        QueryWrapper<BmsDishesEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(StringUtils.isNotBlank(canteenId), "canteen_id", canteenId);
        wrapper.eq(StringUtils.isNotBlank(typeId), "type_id", typeId);

        return wrapper;
    }

    @Override
    public Result<PageData<BmsDishesDTO>> getPage(Map<String, Object> params) {
        IPage<BmsDishesEntity> page = getPage(params, "d.create_date", false);
        List<BmsDishesDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsDishesDTO>>().ok(getPageData(resultList, page.getTotal(), BmsDishesDTO.class));
    }

    @Override
    public BmsDishesDTO selectDishes(Long id) {
        return baseDao.selectDishes(id);
    }

    @Override
    public List<BmsDishesDTO> export(Map<String, Object> params) {
        return baseDao.export(params);
    }

    @Override
    public List<BmsDishesDTO> selectBaseDishesInfo(BmsDishesDTO dto) {
        return baseDao.selectBaseDishesInfo(dto);
    }
}