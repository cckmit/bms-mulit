package cn.amigosoft.modules.visit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 人员报备审批表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_visit_apply_verify")
public class BmsVisitApplyVerifyEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 申请ID bms_visit_apply表
     */
	private Long applyId;
    /**
     * 审批意见 （1：同意 2：拒绝）
     */
	private Integer status;
    /**
     * 回复意见
     */
	private String replyContent;
	/**
	 * 下一个审批人员ID
	 */
	private Long nextUserId;
	/**
	 * 级别
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