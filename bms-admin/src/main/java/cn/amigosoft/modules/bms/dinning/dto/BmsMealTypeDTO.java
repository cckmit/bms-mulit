package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 餐别表
 */
@Data
@ApiModel(value = "餐别表 ")
public class BmsMealTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "餐别名称")
    private String name;

    @ApiModelProperty(value = "提前报餐时间 （单位：小时）")
    private Integer advanceOrderTime;

    @ApiModelProperty(value = "用餐开始时间")
    private String beginTime;

    @ApiModelProperty(value = "用餐结束时间")
    private String endTime;

    @ApiModelProperty(value = "状态 （0：上线 1：下线）")
    private Integer status;

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

}
