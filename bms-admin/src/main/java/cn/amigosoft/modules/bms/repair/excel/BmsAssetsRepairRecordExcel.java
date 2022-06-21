package cn.amigosoft.modules.bms.repair.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 资产维修记录表 
 */
@Data
public class BmsAssetsRepairRecordExcel {
    @Excel(name = "ID")
    private Long id;
    @Excel(name = "维修表ID(bms_assets_repair)")
    private Integer assetsRepairId;
    @Excel(name = "被指派用户ID(sys_user)")
    private Long toUserId;
    @Excel(name = "处理状态 （0：待审批 1：已驳回 2：待处理（已审批） 3：处理中 4：待评价 5：已完成）")
    private Integer status;
    @Excel(name = "处理内容")
    private String content;
    @Excel(name = "是否派单 （0:直接处理 1:指派他人）")
    private Integer isDispatch;
    @Excel(name = "删除标识 （0：未删除；1：已删除）")
    private Integer del;
    @Excel(name = "租户ID")
    private Long tenantId;
    @Excel(name = "创建者(sys_user)")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createDate;

}