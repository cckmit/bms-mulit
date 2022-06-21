package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.entity.BmsScanLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扫码记录表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
@Mapper
public interface BmsScanLogDao extends BaseDao<BmsScanLogEntity> {
	
}