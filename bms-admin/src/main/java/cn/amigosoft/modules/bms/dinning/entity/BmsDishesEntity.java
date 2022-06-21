package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 菜品表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_dishes")
public class BmsDishesEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 菜品名称
     */
	private String name;
    /**
     * 菜品类别ID
     */
	private Long typeId;
    /**
     * 供应食堂ID
     */
	private Long canteenId;
    /**
     * 菜品图片
     */
	private String img;
    /**
     * 富文本
     */
	private String richText;
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