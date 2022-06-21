package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 扫码核销表
 */
@Data
public class BmsScanVerificationExcel {

    @Excel(name = "员工部门")
    private String creatorDeptName;

    @Excel(name = "员工名称")
    private String creatorName;

    @Excel(name = "一卡通账号")
    private String cardAccount;

    @Excel(name = "餐别")
    private String mealTypeName;

    @Excel(name = "用餐食堂")
    private String canteenName;

    @Excel(name = "餐品")
    private String itemName;

    @Excel(name = "核销时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date createDate;


}
