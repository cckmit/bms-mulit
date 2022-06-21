package cn.amigosoft.modules.bms.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 小程序绑定表 
 */
@Data
@ApiModel(value = "小程序绑定表 ")
public class BmsWxBindDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "unionId")
	private String unionId;

	@ApiModelProperty(value = "openId")
	private String openId;

	@ApiModelProperty(value = "sessionKey")
	private String sessionKey;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "token")
	private String token;

	@ApiModelProperty(value = "类别 （0-小程序 1-公众号 2-开放平台）")
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


}