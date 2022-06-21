package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 评价用户关联表 
 */
@Data
@ApiModel(value = "评价用户关联表 ")
public class BmsEvaluationUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "评价ID")
	private Long evaluationId;

	@ApiModelProperty(value = "评价用户ID")
	private Long userId;

	@ApiModelProperty(value = "评价内容")
	private String content;

	@ApiModelProperty(value = "图片")
	private String img;

	@ApiModelProperty(value = "评星")
	private Integer star;

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

	@ApiModelProperty(value = "发布人员")
	private String publisher;

	@ApiModelProperty(value = "员工工号")
	private String publisherWorkNo;

	@ApiModelProperty(value = "员工部门")
	private String publisherDept;

}