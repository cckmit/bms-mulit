package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.DiningFoodLibDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodLibPageDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodLibEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜品库表 Dao 接口
 * </p>
 *
 * @author : hupihshi
 * @version : 1.0
 * @date : 2021-04-20 14:31:04
 */
@Mapper
public interface DiningFoodLibDao extends BaseDao<DiningFoodLibEntity> {
        /**
         *  每日菜单-添加餐品列表
         * @param params,page
         * @return
         */
        List<DiningFoodLibDTO> addDailyMenuLibList(@Param("page") IPage<DiningFoodLibEntity> page, @Param("params") Map<String, Object> params);

        List<DiningFoodLibPageDTO> queryDiningFoodLibList(Page page, @Param("ew") QueryWrapper<DiningFoodLibDTO> queryWrapper);

        List<DiningFoodLibEntity> selectDiningList(@Param("ew") QueryWrapper<DiningFoodLibDTO> queryWrapper);
}
