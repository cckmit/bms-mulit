package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 食堂表 
 */
@Data
public class BmsCanteenExcel {
    @Excel(name = "食堂名称")
    private String name;
    @Excel(name = "联系人")
    private String linkUser;
    @Excel(name = "联系电话")
    private String linkUserPhone;
    @Excel(name = "食堂地址")
    private String address;
    @Excel(name = "备注")
    private String remark;

}