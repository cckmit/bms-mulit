package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 一卡通记录表 
 */
@Data
@ApiModel(value = "一卡通记录表 ")
public class BmsCardLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "流水号")
	private Long serialNum;

	@ApiModelProperty(value = "账号")
	private String account;

	@ApiModelProperty(value = "姓名")
	private String realName;

	@ApiModelProperty(value = "个人编号")
	private String userCode;

	@ApiModelProperty(value = "卡片类型")
	private String cardType;

	@ApiModelProperty(value = "交易区号")
	private String businessArea;

	@ApiModelProperty(value = "交易站点")
	private String businessSite;

	@ApiModelProperty(value = "交易地点")
	private String businessAddress;

	@ApiModelProperty(value = "终端编号")
	private String terminalCode;

	@ApiModelProperty(value = "交易类型")
	private String businessType;

	@ApiModelProperty(value = "交易钱包")
	private String businessPurse;

	@ApiModelProperty(value = "交易金额")
	private String businessMoney;

	@ApiModelProperty(value = "卡余额")
	private String cardBalance;

	@ApiModelProperty(value = "库余额")
	private String stockBalance;

	@ApiModelProperty(value = "钱包流水")
	private String purseSerial;

	@ApiModelProperty(value = "交易时间")
	private String businessDate;

	@ApiModelProperty(value = "到账时间")
	private String transferredDate;

	@ApiModelProperty(value = "账户号")
	private String businessCode;

	@ApiModelProperty(value = "商户名称")
	private String businessName;

	@ApiModelProperty(value = "优惠金额")
	private String discountMoney;

	@ApiModelProperty(value = "押金金额")
	private String deposit;

	@ApiModelProperty(value = "卡费金额")
	private String cardFees;

	@ApiModelProperty(value = "手续费用")
	private String charges;

	@ApiModelProperty(value = "操作员号")
	private String operatorCode;

	@ApiModelProperty(value = "卡户部门")
	private String accountDept;

	@ApiModelProperty(value = "交易方式")
	private String transactionMode;

	@ApiModelProperty(value = "支付渠道")
	private String paymentChannel;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "餐别ID")
	private Long mealTypeId;

	@ApiModelProperty(value = "删除标识 （0：未删除 1：已删除）")
	private Integer del;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新人")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "导入批次编号")
	private String importCode;

}