package cn.amigosoft.modules.bms.visit.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 人员报备审批表 
 */
@Data
public class BmsVisitApplyVerifyExcel {
    @Excel(name = "ID")
    private Long id;
    @Excel(name = "申请ID bms_visit_apply表")
    private Long applyId;
    @Excel(name = "审批意见 （1：同意 2：拒绝）")
    private Integer status;
    @Excel(name = "回复意见")
    private String replyContent;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "删除标识 （0：未删除 1：已删除）")
    private Integer del;
    @Excel(name = "租户ID")
    private Long tenantId;
    @Excel(name = "创建人")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "更新人")
    private Long updater;
    @Excel(name = "更新时间")
    private Date updateDate;

}