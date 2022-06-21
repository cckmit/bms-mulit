package cn.amigosoft.modules.wxapp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class WxappBindDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "加密")
    private String encryptedData;

    @ApiModelProperty(value="iv")
    private String iv;

    @ApiModelProperty(value = "code")
    private String code;
}
