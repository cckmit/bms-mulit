package cn.amigosoft.modules.bms.visit.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 访问地点表 
 */
@Data
public class BmsVisitAddressExcel {

    @Excel(name = "地点名称")
    private String name;

    @Excel(name = "备注")
    private String remark;

}