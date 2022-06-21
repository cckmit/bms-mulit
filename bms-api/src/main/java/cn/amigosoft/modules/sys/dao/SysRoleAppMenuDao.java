package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.entity.SysRoleAppMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色小程序菜单关系表 
 */
@Mapper
public interface SysRoleAppMenuDao extends BaseDao<SysRoleAppMenuEntity> {
	
}