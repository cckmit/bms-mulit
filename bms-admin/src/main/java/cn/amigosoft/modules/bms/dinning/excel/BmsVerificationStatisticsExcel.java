package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 核销统计表
 */
@Data
public class BmsVerificationStatisticsExcel {

    @Excel(name = "食堂名称")
    private String canteenName;

    @Excel(name = "部门")
    private String deptName;

    @Excel(name = "姓名")
    private String userName;

    @Excel(name = "一卡通账号")
    private String cardAccount;

    @Excel(name = "日期")
    private String eatDate;

    @Excel(name = "餐别")
    private String mealTypeName;

    @Excel(name = "报餐状态 ")
    private String orderStatusName;

    @Excel(name = "刷卡次数")
    private Integer swipeCardCount;

    @Excel(name = "消费金额")
    private String businessMoney;

    @Excel(name = "核销状态", replace = {"未核销_0", "已核销_1"})
    private Integer status;

    @Excel(name = "核销时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss", needMerge = true)
    private Date endDate;

}
