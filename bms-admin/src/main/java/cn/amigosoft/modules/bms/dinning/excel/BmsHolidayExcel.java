package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 节假日表
 */
@Data
public class BmsHolidayExcel {

    @Excel(name = "假日", width = 20)
    private String holiday;

    @Excel(name = "备注")
    private String remark;

}
