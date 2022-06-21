package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 订单详情表
 */
@Data
public class BmsOrderDetailExcel {

    @Excel(name = "报餐时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date createDate;

    @Excel(name = "报餐部门")
    private String orderUserDept;

    @Excel(name = "报餐员工")
    private String orderUserName;

    @Excel(name = "一卡通账号")
    private String cardAccount;

    @Excel(name = "员工身份")
    private String orderUserIdentity;

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

}
