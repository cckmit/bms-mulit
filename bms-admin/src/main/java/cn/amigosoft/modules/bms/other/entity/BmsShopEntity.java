package cn.amigosoft.modules.bms.other.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 店铺表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_shop")
public class BmsShopEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 店铺名称
     */
	private String name;
    /**
     * 店铺编号
     */
	private String code;
    /**
     * 店铺地址
     */
	private String address;
    /**
     * 联系人ID
     */
	private Long linkUserId;
    /**
     * 备注
     */
	private String remark;
    /**
     * 删除标识 （0：未删除 1：已删除）
     */
	private Integer del;
    /**
     * 租户ID
     */
	private Long tenantId;
    /**
     * 更新人
     */
		@TableField(fill = FieldFill.UPDATE)
	private Long updater;
    /**
     * 更新时间
     */
		@TableField(fill = FieldFill.UPDATE)
	private Date updateDate;
}