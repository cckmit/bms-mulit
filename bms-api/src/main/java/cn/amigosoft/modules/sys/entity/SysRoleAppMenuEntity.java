package cn.amigosoft.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 角色小程序菜单关系表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_app_menu")
public class SysRoleAppMenuEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	private Long roleId;
    /**
     * 小程序菜单ID
     */
	private Long appMenuId;
    /**
     * 删除标识 （0：未删除；1：已删除）
     */
	private Integer del;
    /**
     * 租户ID
     */
	private Long tenantId;
    /**
     * 更新者
     */
		@TableField(fill = FieldFill.UPDATE)
	private Long updater;
    /**
     * 更新时间
     */
		@TableField(fill = FieldFill.UPDATE)
	private Date updateDate;
}