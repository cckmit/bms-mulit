package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 订单详情表
 */
@Data
public class BmsOrderDetail2Excel {

    @Excel(name = "餐品类型")
    private String itemTypeName;

    @Excel(name = "餐品", width = 20)
    private String itemName;

    @Excel(name = "用餐食堂", width = 20)
    private String canteenName;

    @Excel(name = "餐别")
    private String mealTypeName;

    @Excel(name = "用餐日期", width = 20)
    private String eatDate;

    @Excel(name = "核销状态")
    private String statusName;

}