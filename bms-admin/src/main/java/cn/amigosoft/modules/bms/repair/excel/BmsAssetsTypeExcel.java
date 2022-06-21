package cn.amigosoft.modules.bms.repair.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 资产类别表
 */
@Data
public class BmsAssetsTypeExcel {

    @Excel(name = "类别名称")
    private String name;

    @Excel(name = "备注")
    private String remark;

}