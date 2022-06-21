package cn.amigosoft.modules.bms.other.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺表
 */
@Data
@ApiModel(value = "店铺表 ")
public class BmsShopDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "店铺名称")
    private String name;

    @ApiModelProperty(value = "店铺编号")
    private String code;

    @ApiModelProperty(value = "店铺地址")
    private String address;

    @ApiModelProperty(value = "联系人ID")
    private Long linkUserId;

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

    @ApiModelProperty(value = "联系人")
    private String linkUserName;

    @ApiModelProperty(value = "联系电话")
    private String linkUserMobile;

}