package cn.amigosoft.modules.sys.service;

import cn.amigosoft.common.service.CrudService;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dto.SysAppMenuDTO;
import cn.amigosoft.modules.sys.entity.SysAppMenuEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 小程序菜单表 
 */
public interface SysAppMenuService extends CrudService<SysAppMenuEntity, SysAppMenuDTO> {

    List<SysAppMenuDTO> selectMenuByUserInfo(HttpServletRequest request);

    List<SysAppMenuDTO> getUserAppMenuList(UserDetail user, Integer type);

    List<Long> getAppMenuIdList(Long roleId);

    void saveOrUpdate(Long roleId, List<Long> appMenuIdList);
}