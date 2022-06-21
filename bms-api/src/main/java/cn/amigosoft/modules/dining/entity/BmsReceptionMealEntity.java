package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 接待餐表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_reception_meal")
public class BmsReceptionMealEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 用餐日期
     */
	private String eatDate;
    /**
     * 餐别ID
     */
	private Long mealTypeId;
    /**
     * 食堂ID
     */
	private Long canteenId;
	/**
     * 状态（0：待审批 1：已同意 2：已拒绝）
     */
	private Integer status;
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