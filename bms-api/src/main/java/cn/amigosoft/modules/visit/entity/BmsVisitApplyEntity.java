package cn.amigosoft.modules.visit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 人员报备申请表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_visit_apply")
public class BmsVisitApplyEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 被访人ID
     */
	private Long userId;
    /**
     * 访问事项
     */
	private String matter;
    /**
     * 访问开始时间
     */
	private Date beginDate;
    /**
     * 访问结束时间
     */
	private Date endDate;
    /**
     * 访问地点ID bms_visit_address表
     */
	private Long addressId;
    /**
     * 审批人员ID sys_user表
     */
	private Long verifyUserId;
    /**
     * 状态 （0：待审批 1：同意 2：拒绝）
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
     * 创建人openId
     */
	private String creatorOpenId;
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