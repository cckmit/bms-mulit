/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service;

import cn.amigosoft.common.service.BaseService;
import cn.amigosoft.modules.sys.dto.SysDeptDTO;
import cn.amigosoft.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

    List<SysDeptDTO> list(Map<String, Object> params);

    SysDeptDTO get(Long id);

    void save(SysDeptDTO dto);

    void update(SysDeptDTO dto);

    void delete(Long id);

    /**
     * 根据部门ID，获取本部门及子部门ID列表
     * @param id   部门ID
     */
    List<Long> getSubDeptIdList(Long id);

    SysDeptEntity getDeptInfo(Long id);

    void updateManger(SysDeptDTO dto);

    SysDeptEntity getDeptByAllName(String allName);


    /**
     * 超级树（登录用户的顶级租户下的所有部门）
     * @Author yangjian
     * @Date 2020/9/24
     **/
    List<SysDeptDTO> superList(Map<String, Object> params);

}
