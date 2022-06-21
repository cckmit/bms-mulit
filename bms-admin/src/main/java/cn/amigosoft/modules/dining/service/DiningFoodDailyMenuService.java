package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.dining.dto.DiningFoodAddDailyMenuDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodDailyMenuDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodDailyMenuListDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodLibDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodDailyMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  每日菜品 服务接口类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 14:45:58
 */
public interface DiningFoodDailyMenuService extends CrudService<DiningFoodDailyMenuEntity, DiningFoodDailyMenuDTO> {
    /**
     * 每日菜单列表
     * @param params
     * @return
     */
    PageData<DiningFoodDailyMenuListDTO> getDailyData(Map<String, Object> params);
    /**
     * 每日菜单移除
     * @param ids
     * @return
     */
    void removeDailyMenu(Long[] ids);
    /**
     * 指定日期时段内菜单菜品
     * @param params
     * @return
     */
    List<DiningFoodDailyMenuEntity> getTodayMenuList(Map<String, Object> params);
    /**
     * 每日菜单添加
     * @param dto
     * @return
     */
    void addDailyMenu(DiningFoodAddDailyMenuDTO dto);

    /**
     * 将菜品增加到每日菜单
     * @param dto
     */
    void insertDingFoodLibMenu(DiningFoodLibDTO dto, Integer type);

    /**
     * 删除每日菜单
     */
    void deleteDingFoodLibMenuByDiningFoodLibIds(Long[] diningFoodLibids, Integer type);


}

