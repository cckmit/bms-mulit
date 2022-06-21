package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 节假日表
 */
@Data
@ApiModel(value = "节假日表 ")
public class BmsHolidayDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "假日")
	private String holiday;

	@ApiModelProperty(value = "备注")
	private String remark;

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
