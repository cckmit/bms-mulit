package cn.amigosoft.modules.bms.visit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 访问地点用户关联表
 */
@Data
@ApiModel(value = "访问地点用户关联表 ")
public class BmsVisitAddressUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "访问地点ID")
    private Long addressId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "审核人员名称")
    private String verifyUserName;

}
