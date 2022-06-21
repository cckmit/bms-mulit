package cn.amigosoft.modules.area.dto;

import cn.amigosoft.common.validator.group.DefaultGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


/**
 * 区域详细信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-18
 */
@Data
@ApiModel(value = "区域详细信息表")
public class AreaInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "区域ID")
    private Long areaId;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "区域名称")
    @NotEmpty(message = "{area.areaName.require}", groups = DefaultGroup.class)
    private String areaName;

    @ApiModelProperty(value = "区域编号")
    private String areaCode;

    @ApiModelProperty(value = "区域类型（1小区、2商场）")
    private Integer areaType;

    @NotEmpty(message = "{area.address.require}", groups = DefaultGroup.class)
    @ApiModelProperty(value = "省份")
    private String provinceCode;

    @NotEmpty(message = "{area.address.require}", groups = DefaultGroup.class)
    @ApiModelProperty(value = "城市")
    private String cityCode;

    @NotEmpty(message = "{area.address.require}", groups = DefaultGroup.class)
    @ApiModelProperty(value = "县区")
    private String countyCode;

    @NotEmpty(message = "{area.address.require}", groups = DefaultGroup.class)
    @ApiModelProperty(value = "所属街道")
    private String street;

    @ApiModelProperty(value = "物业公司名称")
    private String propertyCompanyName;

    @ApiModelProperty(value = "物业公司电话")
    private String propertyCompanyPhone;

    @ApiModelProperty(value = "物业公司负责人")
    private String propertyCompanyDuty;

    @NotEmpty(message = "{area.address.require}", groups = DefaultGroup.class)
    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "街道办电话")
    private String streetOfficePhone;

    @ApiModelProperty(value = "楼栋数量")
    private Integer buildingNum;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "维度")
    private String latitude;

    @ApiModelProperty(value = "地图缩放等级")
    private Integer mapZoom;

    @ApiModelProperty(value = "开发商")
    private String developer;

    @ApiModelProperty(value = "所属派出所")
    private String policeStation;

    @ApiModelProperty(value = "派出所电话")
    private String policeStationPhone;

    @ApiModelProperty(value = "物业ID")
    private Long propertyId;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新者")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "是否删除（0未删除，1已删除）")
    private Integer del;


}