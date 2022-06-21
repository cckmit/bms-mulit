package cn.amigosoft.modules.bms.visit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 人员报备访客关联表 
 */
@Data
@ApiModel(value = "人员报备访客关联表 ")
public class BmsVisitApplyVisitorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "申请ID")
	private Long applyId;

	@ApiModelProperty(value = "访客姓名")
	private String name;

	@ApiModelProperty(value = "身份证号")
	private String idCard;

	@ApiModelProperty(value = "电话号码")
	private String mobile;

	@ApiModelProperty(value = "单位")
	private String company;

	@ApiModelProperty(value = "来源省")
	private String sourceProvince;

	@ApiModelProperty(value = "来源市")
	private String sourceCity;

	@ApiModelProperty(value = "来源县")
	private String sourceCounty;

	@ApiModelProperty(value = "健康证明 （图片地址，以逗号分割）")
	private String imgs;

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

	@ApiModelProperty(value = "来源地")
	private String source;

}