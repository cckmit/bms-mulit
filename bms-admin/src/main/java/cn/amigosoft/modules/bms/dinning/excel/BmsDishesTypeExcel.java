package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 菜品类别表
 */
@Data
public class BmsDishesTypeExcel {
    @Excel(name = "类别名称")
    private String name;
    @Excel(name = "备注")
    private String remark;
}