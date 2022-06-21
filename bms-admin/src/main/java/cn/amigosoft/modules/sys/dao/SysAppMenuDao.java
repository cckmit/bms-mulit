package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.entity.SysAppMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序菜单表
 */
@Mapper
public interface SysAppMenuDao extends BaseDao<SysAppMenuEntity> {

    List<SysAppMenuEntity> getAppMenuList(Integer type);

    List<SysAppMenuEntity> getAppUserMenuList(@Param("userId") Long id, @Param("type") Integer type);

    List<Long> getAppMenuIdList(Long roleId);

    int deleteRoleAppMenuByRoleId(Long roleId);

    void deleteByRoleIds(Long[] roleIds);
}