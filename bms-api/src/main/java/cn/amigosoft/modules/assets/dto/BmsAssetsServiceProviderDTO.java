package cn.amigosoft.modules.assets.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 服务商表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Data
@ApiModel(value = "服务商表 ")
public class BmsAssetsServiceProviderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "服务商名称")
	private String name;

	@ApiModelProperty(value = "服务类别")
	private Long assetsTypeId;

	@ApiModelProperty(value = "联系人ID")
	private Long linkUserId;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "删除标识 （0：未删除 1：已删除）")
	private Integer del;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "服务类别")
	private String assetsType;

	@ApiModelProperty(value = "联系人姓名")
	private String realName;

	@ApiModelProperty(value = "联系电话")
	private String mobile;

}