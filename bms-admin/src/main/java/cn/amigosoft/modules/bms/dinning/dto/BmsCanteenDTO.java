package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 食堂表 
 */
@Data
@ApiModel(value = "食堂表 ")
public class BmsCanteenDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "食堂名称")
	private String name;

	@ApiModelProperty(value = "二维码标签url")
	private String qrCodeUrl;

	@ApiModelProperty(value = "联系人ID")
	private Long linkUserId;

	@ApiModelProperty(value = "联系人")
	private String linkUser;

	@ApiModelProperty(value = "联系电话")
	private String linkUserPhone;

	@ApiModelProperty(value = "食堂地址")
	private String address;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "删除标识")
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