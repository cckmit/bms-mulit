package cn.amigosoft.modules.bms.other.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 货品表 
 */
@Data
@ApiModel(value = "货品表 ")
public class BmsGoodsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "货品名称")
	private String name;

	@ApiModelProperty(value = "货品编号")
	private String code;

	@ApiModelProperty(value = "货品图片")
	private String img;

	@ApiModelProperty(value = "货品类别ID")
	private Long typeId;

	@ApiModelProperty(value = "货品类别")
	private String typeName;

	@ApiModelProperty(value = "供应店铺ID")
	private Long shopId;

	@ApiModelProperty(value = "供应店铺")
	private String shopName;

	@ApiModelProperty(value = "单价 (元)")
	private String price;

	@ApiModelProperty(value = "单位")
	private String unit;

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


}