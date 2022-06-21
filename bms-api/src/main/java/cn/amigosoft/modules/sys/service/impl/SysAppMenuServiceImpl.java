package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.TreeUtils;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysAppMenuDao;
import cn.amigosoft.modules.sys.dao.SysRoleAppMenuDao;
import cn.amigosoft.modules.sys.dto.SysAppMenuDTO;
import cn.amigosoft.modules.sys.entity.SysAppMenuEntity;
import cn.amigosoft.modules.sys.entity.SysRoleAppMenuEntity;
import cn.amigosoft.modules.sys.enums.SuperAdminEnum;
import cn.amigosoft.modules.sys.service.SysAppMenuService;
import cn.amigosoft.modules.wxapp.dao.BmsWxBindDao;
import cn.amigosoft.modules.wxapp.entity.BmsWxBindEntity;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 小程序菜单表
 */
@Service
public class SysAppMenuServiceImpl extends CrudServiceImpl<SysAppMenuDao, SysAppMenuEntity, SysAppMenuDTO> implements SysAppMenuService {

    @Autowired
    private SysRoleAppMenuDao roleAppMenuDao;

    @Autowired
    private BmsWxBindDao appletBindDao;

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader(Constant.TOKEN_HEADER);

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter(Constant.TOKEN_HEADER);
        }

        return token;
    }

    @Override
    public QueryWrapper<SysAppMenuEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<SysAppMenuEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public List<SysAppMenuDTO> selectMenuByUserInfo(HttpServletRequest request) {
        List<SysAppMenuEntity> appMenuList;
        String token = getRequestToken(request);
        QueryWrapper<BmsWxBindEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", token);
        queryWrapper.orderByDesc("update_date");
        List<BmsWxBindEntity> bindEntities = appletBindDao.selectList(queryWrapper);
        if (bindEntities.size() == 0) {
            appMenuList = baseDao.selectMenuByRoleName(BmsConstant.ROLE_NAME_VISITOR);
        } else {
            BmsWxBindEntity appletBindEntity = bindEntities.get(0);
            Long userId = appletBindEntity.getUserId();
            if (userId == null) {
                appMenuList = baseDao.selectMenuByRoleName(BmsConstant.ROLE_NAME_VISITOR);
            } else {
                appMenuList = baseDao.getAppUserMenuList(userId, null);
            }
        }

        List<SysAppMenuDTO> dtoList = ConvertUtils.sourceToTarget(appMenuList, SysAppMenuDTO.class);
        return TreeUtils.build(dtoList);

    }

    @Override
    public List<SysAppMenuDTO> getUserAppMenuList(UserDetail user, Integer type) {
        List<SysAppMenuEntity> appMenuList;
        //系统管理员，拥有最高权限
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            appMenuList = baseDao.getAppMenuList(type);
        } else {
            appMenuList = baseDao.getAppUserMenuList(user.getId(), type);
        }

        List<SysAppMenuDTO> dtoList = ConvertUtils.sourceToTarget(appMenuList, SysAppMenuDTO.class);

        return TreeUtils.build(dtoList);

    }

    @Override
    public List<Long> getAppMenuIdList(Long roleId) {
        return baseDao.getAppMenuIdList(roleId);
    }

    @Override
    public void saveOrUpdate(Long roleId, List<Long> appMenuIdList) {
        //先删除角色菜单关系
        baseDao.deleteRoleAppMenuByRoleId(roleId);

        //角色没有一个菜单权限的情况
        if (CollUtil.isEmpty(appMenuIdList)) {
            return;
        }

        //保存角色菜单关系
        for (Long menuId : appMenuIdList) {
            SysRoleAppMenuEntity entity = new SysRoleAppMenuEntity();
            entity.setRoleId(roleId);
            entity.setAppMenuId(menuId);
            roleAppMenuDao.insert(entity);
        }

    }

}