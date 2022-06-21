package cn.amigosoft.modules.bms.repair.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 资产表 
 */
@Data
@ApiModel(value = "资产表 ")
public class BmsAssetsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "资产名称")
	private String name;

	@ApiModelProperty(value = "资产编号")
	private String code;

	@ApiModelProperty(value = "资产类别ID(bms_assets_type)")
	private Long assetsTypeId;

	@ApiModelProperty(value = "资产类别")
	private String assetsTypeName;

	@ApiModelProperty(value = "设备厂商")
	private String vendor;

	@ApiModelProperty(value = "设备型号")
	private String model;

	@ApiModelProperty(value = "所在区域")
	private String position;

	@ApiModelProperty(value = "设备图片")
	private String imgs;

	@ApiModelProperty(value = "管理部门ID(sys_dept)")
	private Long deptId;

	@ApiModelProperty(value = "管理部门")
	private String deptName;

	@ApiModelProperty(value = "负责人(sys_user_id)")
	private Long dutyUserId;

	@ApiModelProperty(value = "启用日期")
	private String enableDate;

	@ApiModelProperty(value = "使用年限")
	private Integer useLimit;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "删除标识 （0：未删除 1：已删除）")
	private Integer del;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "负责人名称")
	private String dutyUserName;

	@ApiModelProperty(value = "负责人工号")
	private String dutyUserWorkNo;

}