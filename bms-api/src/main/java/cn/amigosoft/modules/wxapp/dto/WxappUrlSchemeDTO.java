package cn.amigosoft.modules.wxapp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 小程序UrlScheme生成
 */
@Data
@ApiModel(value = "小程序UrlScheme生成 ")
public class WxappUrlSchemeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "进入的页面地址")
    private String path;

    @ApiModelProperty(value = "页面参数")
    private String query;
    private Boolean isExpire = true;
    private Integer expireTime = 5; //分钟

}
