package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.DiningFoodDailyMenuListDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodDailyMenuEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每日菜品 Dao 接口
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 14:45:58
 */
@Mapper
public interface DiningFoodDailyMenuDao extends BaseDao<DiningFoodDailyMenuEntity> {
        /**
         * 每日菜品分页
         * @param params,page
         * @return
         */
        List<DiningFoodDailyMenuListDTO> getDailyData(@Param("page") IPage<DiningFoodDailyMenuEntity> page, @Param("params") Map<String, Object> params);
        /**
         * 每日菜品上下架
         * @param ids,status
         * @return
         */
        void changeStatus(@Param("ids") Long[] ids,@Param("status") Integer status);
        /**
         * 当前餐厅下上架菜品
         * @param params
         * @return
         */
        List<DiningFoodDailyMenuListDTO> getTodayMenuList(@Param("params") Map<String, Object> params);


}
