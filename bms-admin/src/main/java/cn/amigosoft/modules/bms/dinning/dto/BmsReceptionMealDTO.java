package cn.amigosoft.modules.bms.dinning.dto;

import cn.amigosoft.modules.bms.dinning.excel.BmsReceptionMealVisitorExcel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 接待餐表
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

    @ApiModelProperty(value = "餐别")
    private String mealTypeName;

    @ApiModelProperty(value = "食堂ID")
    private Long canteenId;

    @ApiModelProperty(value = "食堂")
    private String canteenName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态名称")
    private String statusName;

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

    @ApiModelProperty(value = "申请员工")
    private String applyUserName;

    @ApiModelProperty(value = "员工工号")
    private String applyUserWorkNo;

    @ApiModelProperty(value = "申请部门")
    private String applyUserDept;

    @ApiModelProperty(value = "申请部门ID")
    private Long applyDeptId;

    @ApiModelProperty(value = "访客信息集合")
    private List<BmsReceptionMealVisitorDTO> visitorList;

    @ApiModelProperty(value = "访客信息")
    private String visitorInfo;

    @ApiModelProperty(value = "搜索开始时间")
    private String searchBeginDate;

    @ApiModelProperty(value = "搜索结束时间")
    private String searchEndDate;

    @ApiModelProperty(value = "审核人员ID")
    private Long verifyId;

    @ApiModelProperty(value = "审批记录集合")
    private List<BmsReceptionMealVerifyDTO> verifyList;

    @ApiModelProperty(value = "访客导出信息")
    private List<BmsReceptionMealVisitorExcel> visitors;

    @ApiModelProperty(value = "订餐人数")
    private Integer orderCount;

}