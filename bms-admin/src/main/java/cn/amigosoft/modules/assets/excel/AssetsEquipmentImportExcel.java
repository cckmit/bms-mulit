package cn.amigosoft.modules.assets.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class AssetsEquipmentImportExcel {
    @Excel(name = "*资产名称",needMerge = true)
    private String name;
    @Excel(name = "*资产类别",needMerge = true)
    private String assetsEquipmentTypeNames;
    @Excel(name = "*设备厂商",needMerge = true)
    private String vendor;
    @Excel(name = "*规格型号",needMerge = true)
    private String model;
    @Excel(name = "*所属区域",needMerge = true)
    private String areaNames;
    @Excel(name = "安装位置",needMerge = true)
    private String position;
    @Excel(name = "*管理部门",needMerge = true)
    private String deptNames;
   /* @Excel(name = "责任人",needMerge = true)
    private String liabilityPersonStaffName;*/
    @Excel(name = "*购置日期",needMerge = true)
    private String purchaseTimeStr;
    @Excel(name = "购置金额(元)",needMerge = true)
    private BigDecimal purchaseAmount;
    @Excel(name = "启用日期",needMerge = true)
    private String enableTimeStr;
    @Excel(name = "使用年限",needMerge = true)
    private Integer useLimit;
    @Excel(name = "是否开启到期提醒",replace = {"否_1", "是_0"},needMerge = true)
    private Integer yearsRemind;
    @Excel(name = "提前提醒天数",needMerge = true)
    private Integer advanceNums;
   /* @Excel(name = "当前使用人",needMerge = true)
    private String currentPersonStaffName;*/
    @Excel(name = "备注",needMerge = true)
    private String remark;
    @Excel(name = "错误信息",needMerge = true)
    private String errorImport;

}
