/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service.impl;

import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dao.SysDeptDao;
import cn.amigosoft.modules.sys.dto.SysDeptDTO;
import cn.amigosoft.modules.sys.entity.SysDeptEntity;
import cn.amigosoft.modules.sys.enums.SuperAdminEnum;
import cn.amigosoft.modules.sys.service.SysDeptService;
import cn.amigosoft.modules.sys.service.SysUserService;
import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.service.impl.BaseServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.TreeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysDeptDTO> list(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        int permissionFlag = sysUserService.checkUserHavePermission(user.getId(), BmsConstant.PERMISSION_VIEW_DEPT_ALL);
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value() && permissionFlag == 0) {
            params.put("deptIdList", getSubDeptIdList(user.getDeptId()));
        }

        //查询部门列表
        List<SysDeptEntity> entityList = baseDao.getList(params);

        List<SysDeptDTO> dtoList = ConvertUtils.sourceToTarget(entityList, SysDeptDTO.class);

        return TreeUtils.build(dtoList);
    }

    @Override
    public SysDeptDTO get(Long id) {
        //超级管理员，部门ID为null
        if (id == null) {
            return null;
        }

        SysDeptEntity entity = baseDao.getById(id);

        return ConvertUtils.sourceToTarget(entity, SysDeptDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDeptDTO dto) {
        SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

        entity.setPids(getPidList(entity.getPid()));
        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDeptDTO dto) {
        SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

        //上级部门不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new RenException(ErrorCode.SUPERIOR_DEPT_ERROR);
        }

        //上级部门不能为下级部门
        List<Long> subDeptList = getSubDeptIdList(entity.getId());
        if (subDeptList.contains(entity.getPid())) {
            throw new RenException(ErrorCode.SUPERIOR_DEPT_ERROR);
        }

        entity.setPids(getPidList(entity.getPid()));
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //判断是否有子部门
        List<Long> subList = getSubDeptIdList(id);
        if (subList.size() > 1) {
            throw new RenException(ErrorCode.DEPT_SUB_DELETE_ERROR);
        }

        //判断部门下面是否有用户
        int count = sysUserService.getCountByDeptId(id);
        if (count > 0) {
            throw new RenException(ErrorCode.DEPT_USER_DELETE_ERROR);
        }

        //删除
        baseDao.deleteById(id);
    }

    @Override
    public List<Long> getSubDeptIdList(Long id) {
        List<Long> deptIdList = baseDao.getSubDeptIdList("%" + id + "%");
        deptIdList.add(id);

        return deptIdList;
    }

    @Override
    public SysDeptEntity getDeptInfo(Long id) {
        return baseDao.getDeptInfo(id);
    }

    @Override
    public void updateManger(SysDeptDTO dto) {
        Long number = baseDao.getMangerAcount(dto.getId());
        if (number == 0) {

        }
        baseDao.insertManger(dto);
    }

    @Override
    public SysDeptEntity getDeptByAllName(String allName) {
        return this.baseDao.getDeptByAllName(allName);
    }

    /**
     * 获取所有上级部门ID
     * @param pid 上级ID
     */
    private String getPidList(Long pid) {
        //顶级部门，无上级部门
        if (Constant.DEPT_ROOT.equals(pid)) {
            return Constant.DEPT_ROOT + "";
        }

        //所有部门的id、pid列表
        List<SysDeptEntity> deptList = baseDao.getIdAndPidList();

        //list转map
        Map<Long, SysDeptEntity> map = new HashMap<>(deptList.size());
        for (SysDeptEntity entity : deptList) {
            map.put(entity.getId(), entity);
        }

        //递归查询所有上级部门ID列表
        List<Long> pidList = new ArrayList<>();
        getPidTree(pid, map, pidList);

        return StringUtils.join(pidList, ",");
    }

    private void getPidTree(Long pid, Map<Long, SysDeptEntity> map, List<Long> pidList) {
        //顶级部门，无上级部门
        if (Constant.DEPT_ROOT.equals(pid)) {
            return;
        }

        //上级部门存在
        SysDeptEntity parent = map.get(pid);
        if (parent != null) {
            getPidTree(parent.getPid(), map, pidList);
        }

        pidList.add(pid);
    }

    /**
     * 超级树（登录用户的顶级租户下的所有部门）
     *
     * @Author yangjian
     * @Date 2020/9/24
     **/
    @Override
    public List<SysDeptDTO> superList(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();

        //查询登录用户的顶级用户的顶级部门Id
        QueryWrapper<SysDeptEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", Constant.DEPT_ROOT);
        SysDeptEntity sysDeptEntity = selectOne(wrapper);

        params.put("deptIdList", getSubDeptIdList(sysDeptEntity.getId()));

        //查询部门列表
        List<SysDeptEntity> entityList = baseDao.getList(params);

        List<SysDeptDTO> dtoList = ConvertUtils.sourceToTarget(entityList, SysDeptDTO.class);

        return TreeUtils.build(dtoList);
    }

}
