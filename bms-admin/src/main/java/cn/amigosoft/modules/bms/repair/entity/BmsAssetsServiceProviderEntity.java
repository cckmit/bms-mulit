package cn.amigosoft.modules.bms.repair.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 服务商表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_assets_service_provider")
public class BmsAssetsServiceProviderEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 服务商名称
     */
	private String name;
    /**
     * 服务类别
     */
	private Long assetsTypeId;
	/**
	 * 联系人ID
	 */
	private Long linkUserId;
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
     * 更新者
     */
		@TableField(fill = FieldFill.UPDATE)
	private Long updater;
    /**
     * 更新时间
     */
		@TableField(fill = FieldFill.UPDATE)
	private Date updateDate;
}