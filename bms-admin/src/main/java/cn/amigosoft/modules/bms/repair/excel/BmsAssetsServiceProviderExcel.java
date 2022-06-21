package cn.amigosoft.modules.bms.repair.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 服务商表 
 */
@Data
public class BmsAssetsServiceProviderExcel {

    @Excel(name = "服务商")
    private String name;

    @Excel(name = "服务类别")
    private String assetsTypeName;

    @Excel(name = "联系人")
    private String linkUserName;

    @Excel(name = "联系电话")
    private String linkUserMobile;

    @Excel(name = "备注")
    private String remark;

}