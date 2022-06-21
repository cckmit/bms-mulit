package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单详情表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
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

	@ApiModelProperty(value = "食堂ID")
	private Long canteenId;

	@ApiModelProperty(value = "餐别ID")
	private Long mealTypeId;

	@ApiModelProperty(value = "用餐日期")
	private String eatDate;

	@ApiModelProperty(value = "状态 （0：未核销 1：已核销）")
	private Integer status;

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

	@ApiModelProperty(value = "食堂名称")
	private String canteenName;

	@ApiModelProperty(value = "餐品图片")
	private String img;

	@ApiModelProperty(value = "餐品名称")
	private String itemName;

	@ApiModelProperty(value = "餐别名称")
	private String typeName;

	@ApiModelProperty(value = "菜品名称")
	private String dishesName;

	@ApiModelProperty(value = "提前报餐时间")
	private Integer advanceOrderTime;

	@ApiModelProperty(value = "用餐开始时间")
	private String beginTime;

	@ApiModelProperty(value = "用餐结束时间")
	private String endTime;

	@ApiModelProperty(value = "真实细分状态 （0：未核销 1：已核销 2：已过期 3：已取消）")
	private Integer realStatus;

	@ApiModelProperty(value = "员工姓名")
	private String staffName;

	@ApiModelProperty(value = "员工部门")
	private String deptName;

	@ApiModelProperty(value = "员工工号")
	private String workNo;

	@ApiModelProperty(value = "餐别")
	private String mealTypeName;

	@ApiModelProperty(value = "报餐人数")
	private Integer orderCount;

	@ApiModelProperty(value = "扫码核销时间")
	private Date scanDate;

}