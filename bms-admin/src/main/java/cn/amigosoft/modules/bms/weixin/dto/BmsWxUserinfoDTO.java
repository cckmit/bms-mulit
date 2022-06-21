package cn.amigosoft.modules.bms.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 小程序用户信息 
 */
@Data
@ApiModel(value = "小程序用户信息 ")
public class BmsWxUserinfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "小程序用户用户唯一标识")
	private String openId;

	@ApiModelProperty(value = "昵称")
	private String nickname;

	@ApiModelProperty(value = "手机")
	private String mobile;

	@ApiModelProperty(value = "头像")
	private String avatarUrl;

	@ApiModelProperty(value = "省市")
	private String provice;

	@ApiModelProperty(value = "城市")
	private String city;

	@ApiModelProperty(value = "县区")
	private String country;

	@ApiModelProperty(value = "性别")
	private Integer gender;

	@ApiModelProperty(value = "语言")
	private String language;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;


}