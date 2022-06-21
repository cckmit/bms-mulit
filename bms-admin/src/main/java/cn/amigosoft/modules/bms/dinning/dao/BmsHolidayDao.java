package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.entity.BmsHolidayEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 节假日表
 */
@Mapper
public interface BmsHolidayDao extends BaseDao<BmsHolidayEntity> {

    void batchInsertHoliday(List<BmsHolidayEntity> list);

    void batchDeleteHoliday(List<BmsHolidayEntity> list);
}
