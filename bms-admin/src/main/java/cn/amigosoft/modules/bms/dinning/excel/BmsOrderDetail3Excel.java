package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 报餐人数
 */
@Data
public class BmsOrderDetail3Excel {

    @Excel(name = "用餐日期", width = 20)
    private String eatDate;

    @Excel(name = "用餐食堂")
    private String canteenName;

    @Excel(name = "餐别")
    private String mealTypeName;

    @Excel(name = "餐品")
    private String itemName;

    @Excel(name = "报餐人数")
    private Integer orderCount;

}