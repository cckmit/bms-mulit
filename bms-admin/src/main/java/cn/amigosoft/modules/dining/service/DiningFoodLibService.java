package cn.amigosoft.modules.dining.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.dining.dto.DiningFoodLibDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodLibPageDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodLibEntity;
import cn.amigosoft.modules.dining.excel.DiningFoodLibExcel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  菜品库表 服务接口类
 * </p>
 *
 * @author : hupihshi
 * @version : 1.0
 * @date : 2021-04-20 14:31:04
 */
public interface DiningFoodLibService extends CrudService<DiningFoodLibEntity, DiningFoodLibDTO> {
    DiningFoodLibDTO getFoodLibNo(DiningFoodLibDTO dto);

    PageData<DiningFoodLibPageDTO> getDiningFoodLibPage(Map<String, Object> params);

    void checkDiningFoodLibExcel(List<DiningFoodLibExcel> DiningFoodLibExcelList, List<DiningFoodLibExcel> errorDiningFoodLibExcelList);

    int checkDiningFoodName(String Name, Long diningRoomId);

    void cronInsertDingFoodLibMenu();


    /**
     * 每日菜单-添加餐品列表
     * @param params
     * @return
     */
    PageData<DiningFoodLibDTO> addDailyMenuList(Map<String, Object> params);

    //校验Dto数据
    Result validateDtoData(DiningFoodLibDTO dto);

    void setFoodLibIsDelete(Long[] ids);
}

