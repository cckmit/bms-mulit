package cn.amigosoft.modules.bms.repair.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 资产表
 */
@Data
public class BmsAssetsExcel {

    @Excel(name = "资产名称")
    private String name;

    @Excel(name = "资产编号")
    private String code;

    @Excel(name = "资产类别")
    private String assetsTypeName;

    @Excel(name = "管理部门")
    private String deptName;

    @Excel(name = "启用日期")
    private String enableDate;

}