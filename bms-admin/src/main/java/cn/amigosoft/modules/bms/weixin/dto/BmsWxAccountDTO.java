package cn.amigosoft.modules.bms.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 平台帐号信息表
 */
@Data
@ApiModel(value = "平台帐号信息表 ")
public class BmsWxAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "类别 0-小程序 1-公众号 2-开放平台")
	private Integer type;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "租户id")
	private Long tenantId;

	@ApiModelProperty(value = "小程序AppID")
	private String appId;

	@ApiModelProperty(value = "小程序AppSecret")
	private String appSecret;

	@ApiModelProperty(value = "访问令牌")
	private String accessToken;

	@ApiModelProperty(value = "访问令牌生成时间")
	private Date tokenCreateTime;

	@ApiModelProperty(value = "访问令牌失效时间")
	private Date tokenInvalidTime;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "此社区是否拥有访客机设备(0没有，1有)")
	private Integer hasVisitorMachine;
}
