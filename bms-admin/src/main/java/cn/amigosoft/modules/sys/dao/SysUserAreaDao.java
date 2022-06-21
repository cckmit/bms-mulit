package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.entity.SysUserAreaEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与区域关系表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-23
 */
@Mapper
public interface SysUserAreaDao extends BaseDao<SysUserAreaEntity> {

    /**
     * 获取用户的区域数据权限列表
     */
    List<Long> getAreaList(Long userId);

}