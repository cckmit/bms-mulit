package cn.amigosoft.modules.bms.other.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 店铺表 
 */
@Data
public class BmsShopExcel {

    @Excel(name = "店铺名称")
    private String name;

    @Excel(name = "店铺编号")
    private String code;

    @Excel(name = "店铺地址")
    private String address;

    @Excel(name = "联系人")
    private String linkUserName;

    @Excel(name = "联系电话")
    private String linkUserMobile;

    @Excel(name = "备注")
    private String remark;

}