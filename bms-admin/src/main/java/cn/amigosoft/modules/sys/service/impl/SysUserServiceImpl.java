/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.RegularUtil;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.weixin.dao.BmsWxBindDao;
import cn.amigosoft.modules.bms.weixin.dao.BmsWxUserinfoDao;
import cn.amigosoft.modules.bms.weixin.entity.BmsWxBindEntity;
import cn.amigosoft.modules.bms.weixin.entity.BmsWxUserinfoEntity;
import cn.amigosoft.modules.security.password.PasswordUtils;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysUserDao;
import cn.amigosoft.modules.sys.dto.SysUserDTO;
import cn.amigosoft.modules.sys.entity.SysDeptEntity;
import cn.amigosoft.modules.sys.entity.SysRoleEntity;
import cn.amigosoft.modules.sys.entity.SysUserEntity;
import cn.amigosoft.modules.sys.enums.SuperAdminEnum;
import cn.amigosoft.modules.sys.excel.SysUserExcel;
import cn.amigosoft.modules.sys.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private BmsWxBindDao wxBindDao;

    @Autowired
    private BmsWxUserinfoDao wxUserinfoDao;

    final String DEFAULT_PASSWORD = "admin345#$%";

    @Override
    public PageData<SysUserDTO> page(Map<String, Object> params) {
        //转换成like
        paramsToLike(params, "username");

        //分页
        IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);

        //普通管理员，只能查询所属部门及子部门的数据 超级管理员或者拥有权限的人可以看到所有数据
        UserDetail user = SecurityUser.getUser();
        int permissionFlag = baseDao.checkUserHavePermission(user.getId(), BmsConstant.PERMISSION_VIEW_USER_ALL);
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value() || permissionFlag > 0) {
            String deptIdListStr = (String) params.get("deptIdList");
            if (!StringUtil.isBlank(deptIdListStr)) {
                params.put("deptIdList", deptIdListStr.split(","));
            }
        } else {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
            params.put("isSuperAdmin", 0);
        }

//        params.put("tenantId", SecurityUser.getUser().getTenantId());

        //查询
        List<SysUserEntity> list = baseDao.getList(params);

        return getPageData(list, page.getTotal(), SysUserDTO.class);
    }

    @Override
    public List<SysUserDTO> list(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据 超级管理员或者拥有权限的人可以看到所有数据
        UserDetail user = SecurityUser.getUser();
        int permissionFlag = baseDao.checkUserHavePermission(user.getId(), BmsConstant.PERMISSION_VIEW_USER_ALL);
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value() && permissionFlag == 0) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }

        List<SysUserEntity> entityList = baseDao.getList(params);

        return ConvertUtils.sourceToTarget(entityList, SysUserDTO.class);
    }

    @Override
    public SysUserDTO get(Long id) {
        SysUserEntity entity = baseDao.getById(id);

        return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
    }

    @Override
    public SysUserDTO getByUsername(String username) {
        SysUserEntity entity = baseDao.getByUsername(username);
        return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserDTO dto) {
        SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

        // 临时添加后期删除
        entity.setTenantId((long) 10000);
        //密码加密
        String password = PasswordUtils.encode(entity.getPassword());
        entity.setPassword(password);

        //保存用户
        entity.setSuperAdmin(SuperAdminEnum.NO.value());
        insert(entity);

        //保存角色用户关系
        sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserDTO dto) {
        SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

        //密码加密
        if (StringUtils.isBlank(dto.getPassword())) {
            entity.setPassword(null);
        } else {
            String password = PasswordUtils.encode(entity.getPassword());
            entity.setPassword(password);
        }
        Long id = dto.getId();
        SysUserEntity data = baseDao.selectById(id);
        if (!data.getMobile().equals(entity.getMobile())) {
            QueryWrapper<BmsWxBindEntity> deleteBind = new QueryWrapper<>();
            deleteBind.eq("user_id", id);
            wxBindDao.delete(deleteBind);

            QueryWrapper<BmsWxUserinfoEntity> deleteUserInfo = new QueryWrapper<>();
            deleteUserInfo.eq("user_id", id);
            wxUserinfoDao.delete(deleteUserInfo);
        }
        //更新用户
        updateById(entity);
        List<Long> roleIdList = dto.getRoleIdList();

        //更新角色用户关系
        if (roleIdList != null && roleIdList.size() > 0) {
            sysRoleUserService.saveOrUpdate(entity.getId(), roleIdList);
        }
    }

    @Override
    public void delete(Long[] ids) {
        //删除用户
        baseDao.deleteBatchIds(Arrays.asList(ids));

        //删除角色用户关系
        sysRoleUserService.deleteByUserIds(ids);

        // 删除小程序关联信息
        for (Long id : ids) {
            QueryWrapper<BmsWxBindEntity> deleteBind = new QueryWrapper<>();
            deleteBind.eq("user_id", id);
            wxBindDao.delete(deleteBind);

            QueryWrapper<BmsWxUserinfoEntity> deleteUserInfo = new QueryWrapper<>();
            deleteUserInfo.eq("user_id", id);
            wxUserinfoDao.delete(deleteUserInfo);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        newPassword = PasswordUtils.encode(newPassword);

        baseDao.updatePassword(id, newPassword);
    }

    @Override
    public int getCountByDeptId(Long deptId) {
        return baseDao.getCountByDeptId(deptId);
    }

    @Override
    public List<Long> getUserIdListByDeptId(List<Long> deptIdList) {
        return baseDao.getUserIdListByDeptId(deptIdList);
    }

    @Override
    public List<SysUserEntity> Userlist(Map<String, Object> params) {

        return baseDao.getUserlist(params);
    }

    @Override
    public PageData<SysUserEntity> pageUser(Map<String, Object> params) {
        IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);
        List<SysUserEntity> list = baseDao.getList(params);
        System.out.println(list);
        return getPageData(list, page.getTotal(), SysUserEntity.class);
    }

    private Map<String, Long> getDeptMap() {
        // 部门信息
        List<SysDeptEntity> deptList = this.sysDeptService.selectList(new QueryWrapper<>());
        Map<Long, String> deptNameMap = new HashMap<>(deptList.size());
        for (SysDeptEntity dept : deptList) {
            deptNameMap.put(dept.getId(), dept.getName());
        }
        Map<String, Long> result = new HashMap<>(deptList.size());
        for (SysDeptEntity dept : deptList) {
            if (!BmsConstant.DEPT_ROOT_ID.equals(dept.getPid())) {
                String pids = dept.getPids();
                String[] pidArr = pids.split(",");
                List<String> nameList = new ArrayList<>(pidArr.length);
                for (String pid : pidArr) {
                    nameList.add(deptNameMap.get(Long.parseLong(pid)));
                }
                nameList.add(dept.getName());
                String name = StringUtils.join(nameList, "\\");
                result.put(name, dept.getId());
            } else {
                result.put(dept.getName(), dept.getId());
            }
        }
        return result;
    }

    @Override
    public List<SysUserExcel> importExcel(List<SysUserExcel> excelList) {
        // 定义变量
        // 错误list
        List<SysUserExcel> errorList = new ArrayList<>();
        // 角色map
        List<SysRoleEntity> roleList = this.sysRoleService.getRoleName();
        Map<String, Long> roleMap = roleList.stream().collect(Collectors.toMap(SysRoleEntity::getName, SysRoleEntity::getId));
        // 部门名称-ID对应信息
        Map<String, Long> deptMap = getDeptMap();
        // 错误信息
        String errMsg;
        // 循环处理导入数据
        for (SysUserExcel model : excelList) {
            // 重置错误信息
            try {
                // 数据校验
                // 账号
                if (StringUtil.isBlank(model.getUsername())) {
                    throw new Exception("账号不能为空");
                }
                model.setUsername(model.getUsername().trim());
                if (this.getByUsername(model.getUsername()) != null) {
                    throw new Exception("账号已存在");
                }
                // 姓名
                if (StringUtil.isBlank(model.getRealName())) {
                    throw new Exception("真实姓名不能为空");
                }
                model.setRealName(model.getRealName().trim());
                // 工作状态
                if (model.getWorkStatus() == null) {
                    throw new Exception("工作状态不能为空");
                }
                // 手机号
                if (StringUtil.isBlank(model.getMobile())) {
                    throw new Exception("手机号不能为空");
                }
                model.setMobile(model.getMobile().trim());
                if (!RegularUtil.isMobile(model.getMobile())) {
                    throw new Exception("手机号格式错误");
                } else {
                    QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("mobile", model.getMobile());
                    if (baseDao.selectOne(queryWrapper) != null) {
                        throw new Exception("该手机号已被绑定");
                    }
                }
                // 部门名称
                if (StringUtil.isBlank(model.getDeptName())) {
                    throw new Exception("部门名称不能为空");
                }
                model.setDeptName(model.getDeptName().trim());
                Long deptId = deptMap.get(model.getDeptName());
                if (deptId == null) {
                    throw new Exception("部门名称不存在");
                }
                // 状态
                if (model.getStatus() == null) {
                    throw new Exception("状态不能为空");
                }
                // 角色
                if (StringUtil.isBlank(model.getRoleName())) {
                    throw new Exception("角色不能为空");
                }
                model.setRoleName(model.getRoleName().trim());
                Set<Long> role = new HashSet<>();
                String roleName = model.getRoleName();
                String[] roleArr = roleName.split(",");
                for (String r : roleArr) {
                    Long roleId = roleMap.get(r);
                    if (roleId != null) {
                        role.add(roleId);
                    }
                }
                if (role.size() == 0) {
                    throw new Exception("角色不存在");
                }
                if (model.getWorkNo() != null) {
                    model.setWorkNo(model.getWorkNo().trim());
                }
                if (model.getCardNo() != null) {
                    model.setCardNo(model.getCardNo().trim());
                }
                if (model.getEmail() != null) {
                    model.setEmail(model.getEmail().trim());
                }
                if (model.getCode() != null) {
                    model.setCode(model.getCode().trim());
                }
                if (model.getIdentity() != null) {
                    model.setIdentity(model.getIdentity().trim());
                }
                if (model.getCardAccount() != null) {
                    model.setCardAccount(model.getCardAccount().trim());
                    QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("card_account", model.getCardAccount());
                    if (baseDao.selectOne(queryWrapper) != null) {
                        throw new Exception("该外部账号已被绑定");
                    }
                }
                // 取出roleId
                List<Long> roleIds = new ArrayList<>();
                for (Long data : role) {
                    roleIds.add(data);
                }
                // 新增用户信息
                SysUserEntity sysUserEntity = changeExcelToEntity(model, deptId);
                String password = PasswordUtils.encode("wy123456");
                sysUserEntity.setPassword(password);
                this.insert(sysUserEntity);
                // 新增用户角色信息
                this.sysRoleUserService.saveOrUpdate(sysUserEntity.getId(), roleIds);
            } catch (Exception e) {
                errMsg = e.getMessage();
                log.error(errMsg);
                model.setErrorInfo(errMsg);
                errorList.add(model);
            }
        }
        log.info("处理完毕，" +
                "共 " + excelList.size() + " 条数据，" +
                "成功处理 " + (excelList.size() - errorList.size()) + " 条数据，" +
                "处理失败 " + errorList.size() + " 条数据。");
        return errorList;
    }

    @Override
    public String validDto(SysUserDTO dto) {

        String error = "";
        Long tenantId = SecurityUser.getUser().getTenantId();
        // 账号
        if (StringUtil.isNotBlank(dto.getUsername())) {
            if (baseDao.isUsernameExisted(tenantId, dto.getUsername(), dto.getId()) > 0) {
                error = error + "账号已存在;";
            }
        }
        // 邮箱
        if (StringUtil.isNotBlank(dto.getEmail())) {
            if (baseDao.isEmailExisted(tenantId, dto.getEmail(), dto.getId()) > 0) {
                error = error + "邮箱已存在;";
            }
        }
        // 工号
        if (StringUtil.isNotBlank(dto.getWorkNo())) {
            if (baseDao.isWorkNoExisted(tenantId, dto.getWorkNo(), dto.getId()) > 0) {
                error = error + "工号已存在;";
            }
        }
        // 手机号
        if (StringUtil.isNotBlank(dto.getMobile())) {
            if (baseDao.isMobileExisted(tenantId, dto.getMobile(), dto.getId()) > 0) {
                error = error + "手机号已存在;";
            }
        }
        return error;
    }

    @Override
    public SysUserDTO queryMd5Password(Long id) {
        return baseDao.selectMd5Password(id);
    }

    @Override
    public List<SysUserDTO> selectUserByRoleKey(String roleKey) {
        List<SysUserDTO> list = baseDao.selectUserByRoleKey(roleKey);
        return list;
    }

    @Override
    public List<SysUserDTO> selectBaseUserInfo(String value, Long deptId, String mobile, Long id) {
        return baseDao.selectBaseUserInfo(value, deptId, mobile, id);
    }

    @Override
    public List<SysUserDTO> selectUserByPermission(String permission) {
        return baseDao.selectUserByPermission(permission);
    }

    @Override
    public int checkUserHavePermission(Long id, String permission) {
        return baseDao.checkUserHavePermission(id, permission);
    }

    public SysUserEntity changeExcelToEntity(SysUserExcel model, Long deptId) {

        SysUserEntity entity = new SysUserEntity();

        entity.setTenantId(10000L);
        entity.setUsername(model.getUsername());
        entity.setRealName(model.getRealName());
        entity.setWorkStatus(model.getWorkStatus());
        entity.setEmail(model.getEmail());
        entity.setMobile(model.getMobile());
        entity.setDeptId(deptId);
        entity.setWorkNo(model.getWorkNo());
        entity.setStatus(model.getStatus());
        entity.setSuperAdmin(Constant.SUPER_ADMIN.NO);
        entity.setSuperTenant(Constant.SUPER_TENANT.NO);
        entity.setPassword(PasswordUtils.encode(Constant.DEFAULT_PASSWORD));
        entity.setCardNo(model.getCardNo());
        entity.setCode(model.getCode());
        entity.setCardAccount(model.getCardAccount());
        entity.setIdentity(model.getIdentity());
        return entity;
    }

}
