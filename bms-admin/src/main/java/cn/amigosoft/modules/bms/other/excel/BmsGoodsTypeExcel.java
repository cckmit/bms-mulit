package cn.amigosoft.modules.bms.other.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 货品类别表 
 */
@Data
public class BmsGoodsTypeExcel {

    @Excel(name = "类别名称")
    private String name;

    @Excel(name = "类别编号")
    private String code;

    @Excel(name = "备注")
    private String remark;

}