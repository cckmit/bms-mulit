package cn.amigosoft.modules.bms.dinning.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 扫码记录表
 */
@Data
public class BmsScanLogExcel {
    @Excel(name = "ID")
    private Long id;
    @Excel(name = "扫码类型 10：报餐核销")
    private Integer scanType;
    @Excel(name = "扫码参数")
    private String scanParam;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "租户ID")
    private Long tenantId;
    @Excel(name = "创建人")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "更新人")
    private Long updater;
    @Excel(name = "更新时间")
    private Date updateDate;

}
