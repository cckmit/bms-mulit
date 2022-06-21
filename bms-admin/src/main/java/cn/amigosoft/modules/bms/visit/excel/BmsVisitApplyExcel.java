package cn.amigosoft.modules.bms.visit.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 人员报备申请表
 */
@Data
public class BmsVisitApplyExcel {

    @Excel(name = "报备编号", needMerge = true)
    private Long id;

    @Excel(name = "被访人名称", needMerge = true)
    private String userName;

    @Excel(name = "被访人工号", needMerge = true)
    private String userWorkNo;

    @Excel(name = "访问地点", needMerge = true)
    private String addressName;

    @Excel(name = "访问开始时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm", needMerge = true)
    private Date beginDate;

    @Excel(name = "访问结束时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm", needMerge = true)
    private Date endDate;

    @Excel(name = "审批人员", needMerge = true)
    private String verifyUserName;

    @Excel(name = "审批人员工号", needMerge = true)
    private String verifyUserWorkNo;

    @Excel(name = "状态", needMerge = true)
    private String statusName;

    @Excel(name = "申请日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm", needMerge = true)
    private Date createDate;

    @ExcelCollection(name = "访客")
    private List<BmsVisitApplyVisitorExcel> visitors;

}