package cn.amigosoft.modules.visit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 人员报备访客关联表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_visit_apply_visitor")
public class BmsVisitApplyVisitorEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 申请ID bms_visit_apply表
	 */
	private Long applyId;
	/**
     * 访客姓名
     */
	private String name;
    /**
     * 身份证号
     */
	private String idCard;
    /**
     * 电话号码
     */
	private String mobile;
    /**
     * 单位
     */
	private String company;
    /**
     * 来源省
     */
	private String sourceProvince;
    /**
     * 来源市
     */
	private String sourceCity;
    /**
     * 来源县
     */
	private String sourceCounty;
    /**
     * 健康证明 （图片地址，以逗号分割）
     */
	private String imgs;
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