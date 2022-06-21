package cn.amigosoft.common.utils.asersa.dto;

import cn.amigosoft.common.utils.asersa.annotation.rsa.RSAEncryptField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * AES秘钥发送
 *
 * @author xuefenghai
 * @since 1.0.0 2019-05-14
 */
@Data
@ApiModel(value = "AES秘钥发送")
public class AesTokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "aseKey加密串")
    @RSAEncryptField
    private String aeskey;
}