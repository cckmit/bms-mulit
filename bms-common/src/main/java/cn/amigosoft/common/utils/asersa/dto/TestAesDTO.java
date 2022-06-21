package cn.amigosoft.common.utils.asersa.dto;

import cn.amigosoft.common.utils.asersa.annotation.aes.AESEncryptField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 测试实体类
 *
 * @author xuefenghai
 * @since 1.0.0 2019-05-14
 */
@Data
@ApiModel(value = "加密数据测试")
public class TestAesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "请求报文加密串")
    @AESEncryptField
    private String data;

}