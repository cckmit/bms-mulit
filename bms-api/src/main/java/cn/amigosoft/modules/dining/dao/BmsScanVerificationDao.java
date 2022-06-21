package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.entity.BmsScanVerificationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扫码核销表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
@Mapper
public interface BmsScanVerificationDao extends BaseDao<BmsScanVerificationEntity> {
	
}