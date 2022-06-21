package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 评价表
 */
@Data
public class BmsEvaluationExcel {

    @Excel(name = "评价主题")
    private String title;

    @Excel(name = "评价食堂")
    private String canteenName;

    @Excel(name = "发布人员")
    private String publisher;

    @Excel(name = "员工工号")
    private String publisherWorkNo;

    @Excel(name = "发布编号")
    private Long id;

    @Excel(name = "发布时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm")
    private Date createDate;

    @Excel(name = "结束时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm")
    private Date endDate;

}