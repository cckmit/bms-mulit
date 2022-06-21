package cn.amigosoft.modules.bms.other.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 货品表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_goods")
public class BmsGoodsEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 货品名称
     */
	private String name;
    /**
     * 货品编号
     */
	private String code;
    /**
     * 货品图片
     */
	private String img;
    /**
     * 货品类别ID
     */
	private Long typeId;
    /**
     * 供应店铺ID
     */
	private Long shopId;
    /**
     * 单价 (元)
     */
	private String price;
    /**
     * 单位
     */
	private String unit;
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