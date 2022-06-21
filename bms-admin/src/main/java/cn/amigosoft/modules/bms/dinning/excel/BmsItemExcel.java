package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 餐品表
 */
@Data
public class BmsItemExcel {

    @Excel(name = "餐品名称")
    private String name;

    @Excel(name = "餐品类型")
    private String typeName;

    @Excel(name = "餐别")
    private String mealTypeName;

    @Excel(name = "供应食堂")
    private String canteenName;

    @Excel(name = "餐品详情")
    private String dishesName;

    @Excel(name = "状态", replace = {"上线_0", "下线_1"})
    private Integer status;

}
