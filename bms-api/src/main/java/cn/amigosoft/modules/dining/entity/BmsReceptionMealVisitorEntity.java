package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 接待餐访客关联表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_reception_meal_visitor")
public class BmsReceptionMealVisitorEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 接待餐ID
     */
	private Long receptionMealId;
    /**
     * 访客名称
     */
	private String visitorName;
    /**
     * 访客电话
     */
	private String visitorPhone;
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