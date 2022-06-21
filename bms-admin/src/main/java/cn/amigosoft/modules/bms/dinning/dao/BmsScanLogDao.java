package cn.amigosoft.modules.bms.dinning.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.bms.dinning.entity.BmsScanLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扫码记录表
 */
@Mapper
public interface BmsScanLogDao extends BaseDao<BmsScanLogEntity> {

}
