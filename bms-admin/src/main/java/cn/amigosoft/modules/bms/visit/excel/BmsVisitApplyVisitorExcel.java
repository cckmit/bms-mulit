package cn.amigosoft.modules.bms.visit.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 人员报备访客关联表 
 */
@Data
public class BmsVisitApplyVisitorExcel {
    @Excel(name = "访客姓名")
    private String name;
    @Excel(name = "身份证号")
    private String idCard;
    @Excel(name = "电话号码")
    private String mobile;
    @Excel(name = "单位")
    private String company;
    @Excel(name = "来源地")
    private String source;

}