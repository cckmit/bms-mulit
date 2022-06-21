package cn.amigosoft.modules.bms.visit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 申请和地点关联表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_visit_apply_address")
public class BmsVisitApplyAddressEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 申请ID bms_visit_apply表
     */
	private Long applyId;
    /**
     * 访问地点ID bms_visit_address表
     */
	private Long addressId;
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