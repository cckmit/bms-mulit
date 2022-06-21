package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 菜品表
 */
@Data
public class BmsDishesExcel {
    @Excel(name = "菜品名称")
    private String name;
    @Excel(name = "菜品类别")
    private String typeName;
    @Excel(name = "供应食堂")
    private String canteenName;
    @Excel(name = "备注")
    private String remark;
}