package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.entity.DiningAppointmentRecordFoodLibEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 下午茶预约记录菜品关联表 Dao 接口
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:33:57
 */
@Mapper
public interface DiningAppointmentRecordFoodLibDao extends BaseDao<DiningAppointmentRecordFoodLibEntity> {
}
