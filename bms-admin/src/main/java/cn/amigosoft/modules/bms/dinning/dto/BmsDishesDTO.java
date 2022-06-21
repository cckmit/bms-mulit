package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 菜品表 
 */
@Data
@ApiModel(value = "菜品表 ")
public class BmsDishesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "菜品名称")
	private String name;

	@ApiModelProperty(value = "菜品类别")
	private String typeName;

	@ApiModelProperty(value = "菜品类别ID")
	private Long typeId;

	@ApiModelProperty(value = "供应食堂")
	private String canteenName;

	@ApiModelProperty(value = "供应食堂ID")
	private Long canteenId;

	@ApiModelProperty(value = "菜品图片")
	private String img;

	@ApiModelProperty(value = "富文本")
	private String richText;

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