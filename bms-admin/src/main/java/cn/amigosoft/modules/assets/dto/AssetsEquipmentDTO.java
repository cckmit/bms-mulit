package cn.amigosoft.modules.assets.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 资产设备表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2021-05-25
 */
@Data
@ApiModel(value = "资产设备表")
public class AssetsEquipmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "所属区域(iotsaas_area)")
	private Long areaId;

	@ApiModelProperty(value = "责任人(iotsaas_person_staff)")
	private Long liabilityPersonStaffId;

	@ApiModelProperty(value = "当前使用人(iotsaas_person_staff)")
	private Long currentPersonStaffId;

	@ApiModelProperty(value = "管理部门(sys_dept)")
	private Long deptId;

	@ApiModelProperty(value = "物联网设备关联ID(iotsaas_device)")
	private Long deviceId;

	@ApiModelProperty(value = "资产类别(iotsaas_assets_equipment_type)")
	private Long assetsEquipmentTypeId;

	@ApiModelProperty(value = "资产名称")
	private String name;

	@ApiModelProperty(value = "资产编码")
	private String code;

	@ApiModelProperty(value = "设备编号")
	private String equipmentNo;

	@ApiModelProperty(value = "设备厂商")
	private String vendor;

	@ApiModelProperty(value = "规格型号")
	private String model;

	@ApiModelProperty(value = "二维码标签")
	private String qrCodeUrl;

	@ApiModelProperty(value = "安装位置")
	private String position;

	@ApiModelProperty(value = "购置日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date purchaseTime;

	@ApiModelProperty(value = "购置金额 （单位：分）")
	private BigDecimal purchaseAmount;

	@ApiModelProperty(value = "启用时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date enableTime;

	@ApiModelProperty(value = "使用年限")
	private Integer useLimit;

	@ApiModelProperty(value = "年限提醒开关 （0.打开、1.关闭）")
	private Integer yearsRemind;

	@ApiModelProperty(value = "提前提醒天数")
	private Integer advanceNums;

	@ApiModelProperty(value = "设备图片")
	private String imgs;

	@ApiModelProperty(value = "说明书")
	private String manual;

	@ApiModelProperty(value = "竣工验收报告")
	private String completeAcceptance;

	@ApiModelProperty(value = "资产状态 （1.在用、2.闲置、3.报废）")
	private Integer status;

	@ApiModelProperty(value = "维修状态 （0.正常、1.维修中）")
	private Integer maintainStatus;

	@ApiModelProperty(value = "删除状态 （0.正常、1.删除）")
	private Integer del;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "管理部门")
	private String dept;

	@ApiModelProperty(value = "所属区域名称")
	private String areaName;

	@ApiModelProperty(value = "资产类别")
	private String assetsType;

	@ApiModelProperty(value = "责任人")
	private String liabilityPerson;

	@ApiModelProperty(value = "责任人电话")
	private String phone;

	@ApiModelProperty(value = "当前使用人")
	private String currentPerson;

	@ApiModelProperty(value = "资产到期日期")
	private Date expireDate;

	@ApiModelProperty(value="记录查询ID")
	private Long recordId;

}