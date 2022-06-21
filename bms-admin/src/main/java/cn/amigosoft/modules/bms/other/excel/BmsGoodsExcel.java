package cn.amigosoft.modules.bms.other.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 货品表 
 */
@Data
public class BmsGoodsExcel {

    @Excel(name = "货品名称")
    private String name;

    @Excel(name = "货品编号")
    private String code;

    @Excel(name = "货品类别")
    private String typeName;

    @Excel(name = "供应店铺")
    private String shopName;

    @Excel(name = "单价(元)")
    private String price;

    @Excel(name = "单位")
    private String unit;

}