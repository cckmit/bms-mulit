package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 核销统计表
 */
@Data
@ApiModel(value = "核销统计表 ")
public class BmsVerificationStatisticsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "日期")
    private String eatDate;

    @ApiModelProperty(value = "餐别ID")
    private Long mealTypeId;

    @ApiModelProperty(value = "报餐状态 （0：未报）")
    private Integer orderStatus;

    @ApiModelProperty(value = "报餐状态")
    private String orderStatusName;

    @ApiModelProperty(value = "刷卡次数")
    private Integer swipeCardCount;

    @ApiModelProperty(value = "消费金额")
    private String businessMoney;

    @ApiModelProperty(value = "核销状态 （0：未核销 1：已核销）")
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

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "部门")
    private String deptName;

    @ApiModelProperty(value = "餐别")
    private String mealTypeName;

    @ApiModelProperty(value = "订单详情ID")
    private Long orderDetailId;

    @ApiModelProperty(value = "食堂名称")
    private String canteenName;

    @ApiModelProperty(value = "一卡通账号")
    private String cardAccount;

}
