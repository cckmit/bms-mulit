package cn.amigosoft.modules.bms.dinning.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 扫码核销表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_scan_verification")
public class BmsScanVerificationEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
	private Long orderId;
    /**
     * 订单详情ID
     */
	private Long orderDetailId;
    /**
     * 食堂ID
     */
	private Long canteenId;
    /**
     * 扫码记录表ID
     */
	private Long scanLogId;
    /**
     * 备注
     */
	private String remark;
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
