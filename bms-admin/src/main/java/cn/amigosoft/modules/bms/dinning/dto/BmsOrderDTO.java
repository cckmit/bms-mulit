package cn.amigosoft.modules.bms.dinning.dto;

import cn.amigosoft.modules.bms.dinning.excel.BmsOrderDetail2Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 订单表
 */
@Data
@ApiModel(value = "订单表 ")
public class BmsOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "类型（0：报餐）")
    private Integer type;

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

    @ApiModelProperty(value = "报餐员工")
    private String orderUserName;

    @ApiModelProperty(value = "员工工号")
    private String orderUserWorkNo;

    @ApiModelProperty(value = "报餐部门ID")
    private Long orderUserDeptId;

    @ApiModelProperty(value = "报餐部门")
    private String orderUserDept;

    @ApiModelProperty(value = "餐别ID")
    private Long mealTypeId;

    @ApiModelProperty(value = "已选餐品")
    private List<BmsOrderDetailDTO> orderDetailList;

    @ApiModelProperty(value = "已选餐品导出信息")
    private List<BmsOrderDetail2Excel> detailList;

}