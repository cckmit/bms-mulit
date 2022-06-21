package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 接待餐表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-22
 */
@Data
@ApiModel(value = "接待餐表 ")
public class BmsReceptionMealDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用餐日期")
    private String eatDate;

    @ApiModelProperty(value = "餐别ID")
    private Long mealTypeId;

    @ApiModelProperty(value = "食堂ID")
    private Long canteenId;

    @ApiModelProperty(value = "状态 （0：待审批 1：已同意 2：已拒绝）")
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

    @ApiModelProperty(value = "餐别名称")
    private String mealTypeName;

    @ApiModelProperty(value = "食堂名称")
    private String canteenName;

    @ApiModelProperty(value = "申请人姓名")
    private String realName;

    @ApiModelProperty(value = "申请人工号")
    private String workNo;

    @ApiModelProperty(value = "申请人电话")
    private String mobile;

    @ApiModelProperty(value = "访客列表")
    private List<BmsReceptionMealVisitorDTO> visitorDTOList;

    @ApiModelProperty(value = "用餐开始时间")
    private String beginTime;

    @ApiModelProperty(value = "主管审批人员ID")
    private Long verifyId;

    @ApiModelProperty(value = "审批流程")
    private List<BmsReceptionMealVerifyDTO> verifyList;

    @ApiModelProperty(value = "当前登录用户ID")
    private Long currentUserId;

    @ApiModelProperty(value = "申请人名称")
    private String applyUserName;

}