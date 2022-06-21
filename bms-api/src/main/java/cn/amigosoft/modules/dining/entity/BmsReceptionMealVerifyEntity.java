package cn.amigosoft.modules.dining.entity;

import cn.amigosoft.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 接待餐审批表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_reception_meal_verify")
public class BmsReceptionMealVerifyEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 接待餐ID
     */
	private Long receptionMealId;
    /**
     * 审批意见 （0：同意 1：驳回）
     */
	private Integer opinion;
    /**
     * 回复意见
     */
	private String replyContent;
	/**
	 * 下一个审核人员ID
	 */
	private Long nextUserId;
    /**
     * 级别 （0：提交申请 1：主管审批 2：办公室审批）
     */
	private Integer level;
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