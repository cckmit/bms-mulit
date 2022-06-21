package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.entity.BmsCardLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 一卡通记录表
 */
@Mapper
public interface BmsCardLogDao extends BaseDao<BmsCardLogEntity> {

    int deleteDuplicateData();

    int updateMealTypeId();

    int batchInsertCardLog(List<BmsCardLogEntity> cardLogList);

}
