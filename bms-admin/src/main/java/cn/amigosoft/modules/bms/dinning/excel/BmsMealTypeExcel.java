package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 餐别表
 */
@Data
public class BmsMealTypeExcel {

    @Excel(name = "餐别名称")
    private String name;

    @Excel(name = "用餐开始时间")
    private String beginTime;

    @Excel(name = "用餐结束时间")
    private String endTime;

    @Excel(name = "报餐时间")
    private Integer advanceOrderTime;

    @Excel(name = "状态", replace = {"上线_0", "下线_1"})
    private Integer status;

    @Excel(name = "备注")
    private String remark;

}
