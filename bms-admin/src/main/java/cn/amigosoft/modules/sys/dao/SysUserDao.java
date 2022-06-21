/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

    List<SysUserEntity> getList(Map<String, Object> params);

    SysUserEntity getById(Long id);

    SysUserEntity getByUsername(String username);

    int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    /**
     * 根据部门ID，查询用户数
     */
    int getCountByDeptId(Long deptId);

    /**
     * 根据部门ID,查询用户ID列表
     */
    List<Long> getUserIdListByDeptId(List<Long> deptIdList);

    List<SysUserEntity> getUserlist(Map<String, Object> params);

    int isMobileExisted(@Param("tenantId") Long tenantId, @Param("mobile") String mobile, @Param("id") Long id);

    int isUsernameExisted(@Param("tenantId") Long tenantId, @Param("username") String username, @Param("id") Long id);

    int isEmailExisted(@Param("tenantId") Long tenantId, @Param("email") String email, @Param("id") Long id);

    int isWorkNoExisted(@Param("tenantId") Long tenantId, @Param("workNo") String workNo, @Param("id") Long id);

    SysUserDTO selectMd5Password(Long id);

    List<SysUserDTO> selectUserByRoleKey(String roleKey);

    List<SysUserDTO> selectBaseUserInfo(@Param("value") String value, @Param("deptId") Long deptId, @Param("mobile") String mobile, @Param("id") Long id);

    List<SysUserDTO> selectBaseUserInfoList(String[] userIds);

    List<String> selectUserIdsByDeptId(String deptId);

    /**
     * 校验用户是否拥有该权限
     */
    int checkUserHavePermission(@Param("userId") Long userId, @Param("permission") String permission);

    List<SysUserDTO> selectUserByPermission(String permission);

}