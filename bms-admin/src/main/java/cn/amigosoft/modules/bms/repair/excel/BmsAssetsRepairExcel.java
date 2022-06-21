package cn.amigosoft.modules.bms.repair.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 资产维修表
 */
@Data
public class BmsAssetsRepairExcel {

    @Excel(name = "报修编号")
    private Long id;

    @Excel(name = "报修项目")
    private String title;

    @Excel(name = "报修员工")
    private String creatorName;

    @Excel(name = "员工工号")
    private String creatorWorkNo;

    @Excel(name = "员工部门")
    private String creatorDept;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "报修时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm")
    private Date createDate;

    @Excel(name = "维修状态", replace = {"待审批_0", "已驳回_1", "待处理_2", "处理中_3", "待评价_4", "已完成_5"})
    private Integer status;

}