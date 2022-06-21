package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.entity.DeptPositionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门职位表 
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-17
 */
@Mapper
public interface DeptPositionDao extends BaseDao<DeptPositionEntity> {

    List<DeptPositionEntity> getPositionList(Long id);

}