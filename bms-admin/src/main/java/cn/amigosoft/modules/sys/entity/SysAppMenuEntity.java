package cn.amigosoft.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 小程序菜单表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_app_menu")
public class SysAppMenuEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 组件地址
	 */
	private String url;

	/**
     * 上级ID，一级菜单为0
     */
	private Long pid;
    /**
     * 授权
     */
	private String permission;
    /**
     * 类型   0：菜单   1：按钮
     */
	private Integer type;
    /**
     * 菜单图标
     */
	private String icon;
    /**
     * 排序
     */
	private Integer sort;
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