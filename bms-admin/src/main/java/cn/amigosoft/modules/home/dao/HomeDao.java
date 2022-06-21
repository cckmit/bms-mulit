package cn.amigosoft.modules.home.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.common.entity.BaseEntity;
import cn.amigosoft.modules.home.dto.MapDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 院校表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2020-08-21
 */
@Mapper
public interface HomeDao extends BaseDao<BaseEntity> {

}
