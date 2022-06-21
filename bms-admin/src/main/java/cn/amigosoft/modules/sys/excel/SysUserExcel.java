/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 用户管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
public class SysUserExcel {

    @Excel(name = "账号")
    private String username;

    @Excel(name = "部门名称")
    private String deptName;

    @Excel(name = "姓名")
    private String realName;

    @Excel(name = "手机号")
    private String mobile;

    @Excel(name = "园区身份")
    private String identity;

    @Excel(name = "工号")
    private String workNo;

    @Excel(name = "外部账号")
    private String cardAccount;

    @Excel(name = "卡号")
    private String cardNo;

    @Excel(name = "个人编号")
    private String code;

    @Excel(name = "邮箱")
    private String email;

    @Excel(name = "工作状态", replace = {"在职_10", "离职_20", "退休_30", "其他_40"})
    private Integer workStatus;

    @Excel(name = "状态", replace = {"停用_0", "正常_1"})
    private Integer status;

    @Excel(name = "角色(逗号分隔)")
    private String roleName;

    @Excel(name = "错误原因")
    private String errorInfo;

}
