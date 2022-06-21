package cn.amigosoft.modules.bms.dinning.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.dinning.dao.BmsItemDao;
import cn.amigosoft.modules.bms.dinning.dao.BmsItemDishesDao;
import cn.amigosoft.modules.bms.dinning.dto.BmsItemDTO;
import cn.amigosoft.modules.bms.dinning.dto.BmsItemDishesDTO;
import cn.amigosoft.modules.bms.dinning.entity.BmsItemDishesEntity;
import cn.amigosoft.modules.bms.dinning.entity.BmsItemEntity;
import cn.amigosoft.modules.bms.dinning.service.BmsItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 餐品表
 */
@Service
public class BmsItemServiceImpl extends CrudServiceImpl<BmsItemDao, BmsItemEntity, BmsItemDTO> implements BmsItemService {

    @Autowired
    private BmsItemDishesDao itemDishesDao;

    @Override
    public QueryWrapper<BmsItemEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");
        String type = (String) params.get("type");
        String canteenId = (String) params.get("canteenId");
        String mealTypeId = (String) params.get("mealTypeId");

        QueryWrapper<BmsItemEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "id", name);
        wrapper.eq(StringUtils.isNotBlank(type), "type", type);
        wrapper.eq(StringUtils.isNotBlank(canteenId), "canteen_id", canteenId);
        wrapper.eq(StringUtils.isNotBlank(mealTypeId), "meal_type_id", mealTypeId);
        wrapper.eq(BmsConstant.STATUS_COLUMN_NAME, BmsConstant.STATUS_ONLINE);

        return wrapper;
    }

    @Override
    public Result<PageData<BmsItemDTO>> getPage(Map<String, Object> params) {
        IPage<BmsItemEntity> page = getPage(params, "i.create_date", false);
        List<BmsItemDTO> resultList = baseDao.selectPageList(page, params);
        return new Result<PageData<BmsItemDTO>>().ok(getPageData(resultList, page.getTotal(), BmsItemDTO.class));
    }

    @Override
    public void save(BmsItemDTO dto) {
        super.save(dto);
        String dishesIds = dto.getDishesIds();
        if (StringUtils.isNotEmpty(dishesIds)) {
            String[] dishesArr = dishesIds.split(",");
            Long id = dto.getId();
            for (String dishesId : dishesArr) {
                insertItemDishes(id, Long.valueOf(dishesId));
            }
        }
    }

    private void insertItemDishes(Long itemId, Long dishesId) {
        BmsItemDishesEntity dishesEntity = new BmsItemDishesEntity();
        dishesEntity.setItemId(itemId);
        dishesEntity.setDishesId(dishesId);
        itemDishesDao.insert(dishesEntity);
    }

    @Override
    public void update(BmsItemDTO dto) {
        super.update(dto);
        Long id = dto.getId();
        List<String> list = itemDishesDao.selectIdByItemId(id);
        Iterator<String> iterator = list.iterator();
        String dishesIds = dto.getDishesIds();
        List<String> dishesIdList = new ArrayList<>();
        if (!StringUtils.isEmpty(dishesIds)) {
            String[] dishesArr = dishesIds.split(",");
            for (String dishes : dishesArr) {
                dishesIdList.add(dishes);
            }
        }
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (!dishesIdList.contains(next)) {
                itemDishesDao.deleteByItemIdAndDishesId(id, next);
                iterator.remove();
            }
        }
        dishesIdList.removeAll(list);
        for (String dishesId : dishesIdList) {
            insertItemDishes(id, Long.valueOf(dishesId));
        }
    }

    @Override
    public BmsItemDTO selectItemById(Long id) {
        BmsItemDTO itemDTO = baseDao.selectItemById(id);
        List<BmsItemDishesDTO> list = itemDishesDao.selectItemDishesByItemId(id);
        List<Map<String, String>> dishesList = new ArrayList<>();
        for (BmsItemDishesDTO dishesDTO : list) {
            Map<String, String> map = new HashMap<>(list.size());
            map.put("key", String.valueOf(dishesDTO.getDishesId()));
            map.put("label", dishesDTO.getDishesName());
            dishesList.add(map);
        }
        itemDTO.setDishesList(dishesList);
        return itemDTO;
    }

    @Override
    public List<BmsItemDTO> selectExportList(Map<String, Object> params) {
        return baseDao.selectExportList(params);
    }

    @Override
    public List<BmsItemDTO> getItemList() {
        return baseDao.getItemList();
    }
}
