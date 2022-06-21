package cn.amigosoft.modules.assets.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 资产维修处理表 实体类
 * </p>
 *
 * @author : hupishi
 * @version : 1.0
 * @date : 2021-06-01 15:20:45
 */
@Data
public class AssetsRepairDealExcel {

    private static final long serialVersionUID = 1L;

    @Excel(name = "ID")
    private Long id;

    @Excel(name = "租户ID")
    private Long tenantId;

    @Excel(name = "维修表ID(iotsaas_assets_repair)")
    private Long assetsRepairId;

    @Excel(name = "被转派用户ID(iotsaas_person)")
    private Long toPersonId;

    @Excel(name = "处理状态 （0.待处理、1.处理中、2.待评价、3.已完成）")
    private Integer status;

    @Excel(name = "处理内容")
    private String result;

    @Excel(name = "处理图片")
    private String imgs;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "创建者")
    private Long creator;

    @Excel(name = "创建时间")
    private Date createDate;

    @Excel(name = "更新者")
    private Long updater;

    @Excel(name = "更新时间")
    private Date updateDate;

}
