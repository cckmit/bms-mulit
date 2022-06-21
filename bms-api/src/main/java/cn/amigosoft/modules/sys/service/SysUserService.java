/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.BaseService;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import cn.amigosoft.modules.sys.excel.SysUserExcel;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    PageData<SysUserDTO> page(Map<String, Object> params);

    List<SysUserDTO> list(Map<String, Object> params);

    SysUserDTO get(Long id);

    SysUserDTO getByUsername(String username);

    void save(SysUserDTO dto);

    void update(SysUserDTO dto);

    void delete(Long[] ids);

    /**
     * 修改密码
     * @param id           用户ID
     * @param newPassword  新密码
     */
    void updatePassword(Long id, String newPassword);

    /**
     * 根据部门ID，查询用户数
     */
    int getCountByDeptId(Long deptId);

    /**
     * 根据部门ID,查询用户Id列表
     */
    List<Long> getUserIdListByDeptId(List<Long> deptIdList);

    List<SysUserEntity> Userlist(Map<String, Object> params);

    PageData<SysUserEntity> pageUser(Map<String, Object> params);

    List<SysUserExcel> importExcel(List<SysUserExcel> excelList);

    String validDto(SysUserDTO dto);

    SysUserDTO queryMd5Password(Long id);

    List<SysUserDTO> selectUserByRoleKey(String roleKey);

    List<SysUserDTO> selectBaseUserInfo(String value, Long deptId, String mobile, Long id);

    SysUserEntity getSysUserInfo();

    SysUserDTO getUserDetailInfo();

    List<SysUserDTO> filterDeptUser(List<SysUserDTO> list);

    /**
     * 判断是否存在此用户
     * */
    Result<SysUserEntity> isSysUer(String realName, String mobile);
}
