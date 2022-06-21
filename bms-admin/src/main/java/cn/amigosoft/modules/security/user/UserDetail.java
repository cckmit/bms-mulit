/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.security.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String realName;
    private String headUrl;
    private Integer gender;
    private String email;
    private String mobile;
    private Long deptId;
    private String password;
    private Integer status;
    private Integer superAdmin;
    //顶级租户0：否1：顶级
    private Integer superTenant;
    //租户id,表iotsaas_tenant的主键
    private Long tenantId;
    //工作状态10：在职，20：离职，30：退休，40：其它
    private Integer workStatus;
    //工号
    private String workNo;
    /**
     * 部门数据权限
     */
    private List<Long> deptIdList;
    /**
     * 区域数据权限
     */
    private List<Long> areaIdList;

}