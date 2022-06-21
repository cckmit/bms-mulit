package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 接待餐表
 */
@Data
public class BmsReceptionMealExcel {

    @Excel(name = "申请编号", needMerge = true, width = 20)
    private Long id;

    @Excel(name = "申请时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm", needMerge = true, width = 20)
    private Date createDate;

    @Excel(name = "申请部门", needMerge = true)
    private String applyUserDept;

    @Excel(name = "申请员工", needMerge = true)
    private String applyUserName;

    @Excel(name = "员工工号", needMerge = true)
    private String applyUserWorkNo;

    @ExcelCollection(name = "访客信息")
    private List<BmsReceptionMealVisitorExcel> visitors;

}