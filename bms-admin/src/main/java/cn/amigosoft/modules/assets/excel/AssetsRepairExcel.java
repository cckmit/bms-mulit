package cn.amigosoft.modules.assets.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 资产维修表 实体类
 * </p>
 *
 * @author : hupsh
 * @version : 1.0
 * @date : 2021-05-31 11:00:31
 */
@Data
public class AssetsRepairExcel {

    private static final long serialVersionUID = 1L;

    @Excel(name = "ID")
    private Long id;

    @Excel(name = "租户ID")
    private Long tenantId;

    @Excel(name = "报修资产ID(iotsaas_assets_equipment)")
    private Long assetsEquipmentId;

    @Excel(name = "报修资产编码(iotsaas_assets_equipment)")
    private String assetsEquipmentCode;

    @Excel(name = "报修项目")
    private String title;

    @Excel(name = "报修类型")
    private Integer type;

    @Excel(name = "报修描述")
    private String description;

    @Excel(name = "报修图片")
    private String imgs;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "状态 （0.待处理、1.处理中、2.待评价、3.已完成）")
    private Integer status;

    @Excel(name = "处理人ID(iotsaas_person)")
    private Long dealPersonId;

    @Excel(name = "处理结果")
    private String dealResult;

    @Excel(name = "处理结果图片")
    private String dealImgs;

    @Excel(name = "评价评分")
    private Integer grade;

    @Excel(name = "评价描述")
    private String gradeDesc;

    @Excel(name = "评价图片")
    private String gradeImgs;

    @Excel(name = "创建者 （报修人）")
    private Long creator;

    @Excel(name = "创建时间 （报修时间）")
    private Date createDate;

    @Excel(name = "更新者")
    private Long updater;

    @Excel(name = "更新时间")
    private Date updateDate;

}
