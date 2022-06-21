package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 接待餐访客关联表 
 */
@Data
public class BmsReceptionMealVisitorExcel {

    @Excel(name = "访客名称")
    private String visitorName;

    @Excel(name = "访客电话", width = 20)
    private String visitorPhone;

}