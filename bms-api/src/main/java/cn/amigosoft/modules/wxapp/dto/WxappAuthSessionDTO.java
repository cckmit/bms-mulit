package cn.amigosoft.modules.wxapp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 小程序会话
 */
@Data
@ApiModel(value = "小程序会话 ")
public class WxappAuthSessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "租户ID")
	private Long tenantId;
	@ApiModelProperty(value = "wx.login登录获取的js_code")
	private String jsCode;
}
