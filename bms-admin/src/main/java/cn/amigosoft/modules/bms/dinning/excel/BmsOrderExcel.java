package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;

import java.util.List;

/**
 * 订单表
 */
@Data
public class BmsOrderExcel {

    @Excel(name = "报餐编号", needMerge = true, width = 20)
    private Long id;

    @Excel(name = "报餐员工", needMerge = true)
    private String orderUserName;

    @Excel(name = "员工工号", needMerge = true)
    private String orderUserWorkNo;

    @Excel(name = "报餐部门", needMerge = true)
    private String orderUserDept;

    @ExcelCollection(name = "报餐详情")
    private List<BmsOrderDetail2Excel> detailList;

}