package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单详情表
 */
@Data
@ApiModel(value = "订单详情表 ")
public class BmsOrderDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "订单ID")
	private Long orderId;

	@ApiModelProperty(value = "餐品ID")
	private Long itemId;

	@ApiModelProperty(value = "餐别")
	private String mealTypeName;

	@ApiModelProperty(value = "餐品名称")
	private String itemName;

	@ApiModelProperty(value = "食堂ID")
	private Long canteenId;

	@ApiModelProperty(value = "食堂名称")
	private String canteenName;

	@ApiModelProperty(value = "用餐日期")
	private String eatDate;

	@ApiModelProperty(value = "状态 （0：未核销 1：已核销）")
	private Integer status;

	@ApiModelProperty(value = "状态名称")
	private String statusName;

	@ApiModelProperty(value = "扫码核销状态 （0：未核销 1：已核销）")
	private Integer scanStatus;

	@ApiModelProperty(value = "备注")
	private String remark;

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

	@ApiModelProperty(value = "报餐员工")
	private String orderUserName;

	@ApiModelProperty(value = "员工工号")
	private String orderUserWorkNo;

	@ApiModelProperty(value = "员工身份")
	private String orderUserIdentity;

	@ApiModelProperty(value = "报餐部门ID")
	private Long orderUserDeptId;

	@ApiModelProperty(value = "报餐部门")
	private String orderUserDept;

	@ApiModelProperty(value = "餐别ID")
	private Long mealTypeId;

	@ApiModelProperty(value = "餐品类型ID")
	private Long itemTypeId;

	@ApiModelProperty(value = "餐品类型")
	private String itemTypeName;

	@ApiModelProperty(value = "搜索开始时间")
	private String searchBeginDate;

	@ApiModelProperty(value = "搜索结束时间")
	private String searchEndDate;

	@ApiModelProperty(value = "订餐人数")
	private Integer orderCount;

	@ApiModelProperty(value = "提前报餐时间 （单位：小时）")
	private Integer advanceOrderTime;

	@ApiModelProperty(value = "用餐开始时间")
	private String beginTime;

	@ApiModelProperty(value = "一卡通账号")
	private String cardAccount;
}
