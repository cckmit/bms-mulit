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
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.security.password.PasswordUtils;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysDeptDao;
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
    private SysDictService sysDictService;
    @Autowired
    private SysDeptDao sysDeptDao;

    final String DEFAULT_PASSWORD = "admin345#$%";

    @Override
    public PageData<SysUserDTO> page(Map<String, Object> params) {
        //转换成like
        paramsToLike(params, "username");

        //分页
        IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);

        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            String deptIdListStr = (String) params.get("deptIdList");
            if (!StringUtil.isBlank(deptIdListStr)) {
                params.put("deptIdList", deptIdListStr.split(","));
            }
        }
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
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
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
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

    @Override
    public List<SysUserExcel> importExcel(List<SysUserExcel> excelList) {
        // 定义变量
        // 错误list
        List<SysUserExcel> errorList = new ArrayList<>();
        // 角色map
        List<SysRoleEntity> roleList = this.sysRoleService.getRoleName();
        Map<Long, String> roleMap = roleList.stream().collect(Collectors.toMap(SysRoleEntity::getId, SysRoleEntity::getName));
        // 错误信息
        String errMsg;
        // 循环处理导入数据
        for (SysUserExcel model : excelList) {
            // 重置错误信息
            errMsg = "";
            try {
                // 数据校验
                // 用户名
                if (StringUtil.isBlank(model.getUsername())) {
                    throw new Exception("用户名不能为空");
                }
                if (this.getByUsername(model.getUsername()) != null) {
                    throw new Exception("用户名已存在");
                }
                // 姓名
                if (StringUtil.isBlank(model.getRealName())) {
                    throw new Exception("真实姓名不能为空");
                }
                // 工作状态
                if (model.getWorkStatus() == null) {
                    throw new Exception("工作状态不能为空");
                }
                // 邮箱
                if (StringUtil.isBlank(model.getEmail())) {
                    throw new Exception("邮箱不能为空");
                }
                if (!RegularUtil.isEmail(model.getEmail())) {
                    throw new Exception("邮箱格式错误");
                }
                // 手机号
                if (StringUtil.isBlank(model.getMobile())) {
                    throw new Exception("手机号不能为空");
                }
                if (!RegularUtil.isMobile(model.getMobile())) {
                    throw new Exception("手机号格式错误");
                }
                // 部门名称
                if (StringUtil.isBlank(model.getDeptName())) {
                    throw new Exception("部门名称不能为空");
                }
                SysDeptEntity sysDeptEntity = this.sysDeptService.getDeptByAllName(model.getDeptName());
                if (sysDeptEntity == null) {
                    throw new Exception("部门名称不存在");
                }
                // 工号
                if (StringUtil.isBlank(model.getWorkNo())) {
                    throw new Exception("工号不能为空");
                }
                // 状态
                if (model.getStatus() == null) {
                    throw new Exception("状态不能为空");
                }
                // 角色
                if (StringUtil.isBlank(model.getRoleName())) {
                    throw new Exception("角色不能为空");
                }
                Set<Long> role = roleMap.entrySet()
                        .stream()
                        .filter(kvEntry -> Objects.equals(kvEntry.getValue(), model.getRoleName()))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet());
                if (role.size() == 0) {
                    throw new Exception("角色不存在");
                }
                // 取出roleId
                List<Long> roleIds = new ArrayList<>();
                for (Long data : role) {
                    roleIds.add(data);
                }
                // 新增用户信息
                SysUserEntity sysUserEntity = changeExcelToEntity(model, sysDeptEntity.getId());
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
        // 用户名
        if (StringUtil.isNotBlank(dto.getUsername())) {
            if (baseDao.isUsernameExisted(tenantId, dto.getUsername(), dto.getId()) > 0) {
                error = error + "用户名已存在;";
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

        return entity;
    }

    @Override
    public SysUserEntity getSysUserInfo() {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        SysUserEntity userEntity = baseDao.selectById(userId);
        return userEntity;
    }

    @Override
    public SysUserDTO getUserDetailInfo() {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        SysUserDTO userDTO = baseDao.getUserDetailInfo(userId);
        return userDTO;
    }

    @Override
    public List<SysUserDTO> filterDeptUser(List<SysUserDTO> list) {
        UserDetail user = SecurityUser.getUser();
        Long deptId = user.getDeptId();
        List<SysUserDTO> filterUsers = new ArrayList<>();
        for (SysUserDTO dto : list) {
            if (deptId.equals(dto.getDeptId())) {
                filterUsers.add(dto);
            }
        }
        return filterUsers;
    }

    @Override
    public Result<SysUserEntity> isSysUer(String realName, String mobile) {
        Result result = new Result<SysUserEntity>();
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("real_name", realName);
        wrapper.eq("mobile", mobile);
        wrapper.last("limit 1");
        /*Integer count = baseDao.selectCount(wrapper);
        if (count == 1) {
            return baseDao.selectOne(wrapper);
        }*/
        SysUserEntity sysUser = baseDao.selectOne(wrapper);
        // 存在该用户，返回
        if (sysUser != null) {
            return result.ok(sysUser);
        }
        // 不存在该用户，开始判断姓名或手机号出错的可能
        // 姓名
        wrapper = new QueryWrapper<>();
        wrapper.eq("real_name", realName);
        Integer nameCount = baseDao.selectCount(wrapper);
        if (nameCount > 0) {
            return result.error("请确认手机号是否正确");
        }
        // 手机号
        wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer mobileCount = baseDao.selectCount(wrapper);
        if (mobileCount > 0) {
            return result.error("请确认姓名是否正确");
        }
        return result.error("无此被访人信息，请重新填写");
    }
}
