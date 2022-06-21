package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评价用户关联表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_evaluation_user")
public class BmsEvaluationUserEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 评价ID
     */
	private Long evaluationId;
    /**
     * 评价用户ID
     */
	private Long userId;
    /**
     * 评价内容
     */
	private String content;
    /**
     * 图片
     */
	private String img;
    /**
     * 评星
     */
	private Integer star;
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