package cn.amigosoft.modules.bms.other.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 通知表
 */
@Data
public class BmsNoticeExcel {

    @Excel(name = "内容")
    private String content;

    @Excel(name = "状态", replace = {"上线_0", "下线_1"})
    private Integer status;

    @Excel(name = "排序")
    private Integer sort;

    @Excel(name = "备注")
    private String remark;


}