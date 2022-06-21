/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.dto.SysDeptDTO;
import cn.amigosoft.modules.sys.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    List<SysDeptEntity> getList(Map<String, Object> params);

    SysDeptEntity getById(Long id);

    /**
     * 获取所有部门的id、pid列表
     */
    List<SysDeptEntity> getIdAndPidList();

    /**
     * 根据部门ID，获取所有子部门ID列表
     * @param id   部门ID
     */
    List<Long> getSubDeptIdList(String id);

    SysDeptEntity getDeptInfo(Long id);

    void insertManger(SysDeptDTO dto);

    Long getMangerAcount(Long id);

    SysDeptEntity getDeptByAllName(@Param("allName") String allName);

    List<SysDeptDTO> selectBaseDeptInfoList(String[] deptIds);
}
