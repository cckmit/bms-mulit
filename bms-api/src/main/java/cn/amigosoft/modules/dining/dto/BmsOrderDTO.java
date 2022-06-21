package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 订单表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Data
@ApiModel(value = "订单表 ")
public class BmsOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "类型")
	private Integer type;

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

	@ApiModelProperty(value = "餐品数")
	private Integer num;

	@ApiModelProperty(value = "餐品图片")
	private String images;

	@ApiModelProperty(value = "员工姓名")
	private String staffName;

	@ApiModelProperty(value = "员工部门")
	private String deptName;

	@ApiModelProperty(value = "员工工号")
	private String workNo;

	@ApiModelProperty(value = "报餐部门ID")
	private Long orderUserDeptId;

	@ApiModelProperty(value = "报餐部门")
	private String orderUserDept;

	@ApiModelProperty(value = "餐别ID")
	private Long mealTypeId;

	@ApiModelProperty(value = "已选餐品")
	private List<BmsOrderDetailDTO> orderDetailList;

}