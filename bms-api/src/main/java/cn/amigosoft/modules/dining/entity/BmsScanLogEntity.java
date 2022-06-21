package cn.amigosoft.modules.dining.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 扫码记录表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2022-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_scan_log")
public class BmsScanLogEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 扫码类型 10：报餐核销
     */
	private Integer scanType;
    /**
     * 扫码参数
     */
	private String scanParam;
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