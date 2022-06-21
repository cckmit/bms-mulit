package cn.amigosoft.modules.bms.other.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 区域表 
 */
@Data
public class BmsAreaExcel {
    @Excel(name = "名称")
    private String name;
    @Excel(name = "编码")
    private String code;

}