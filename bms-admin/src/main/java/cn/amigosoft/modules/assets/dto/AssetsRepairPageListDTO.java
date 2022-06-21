package cn.amigosoft.modules.assets.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
@ApiModel(value="AssetsRepair对象", description="资产维修表")
@Data
public class AssetsRepairPageListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("报修资产名称")
    private String assetsEquipmentName;

    @ApiModelProperty("资产编号")
    private String assetsEquipmentCode;;

    @ApiModelProperty("所在区域Id")
    private Long areaId;

    @ApiModelProperty("所在区域")
    private String areaName;

    @ApiModelProperty("报修内容")
    private String assetsRepairContent;

    @ApiModelProperty("报修人")
    private String assentsApplicant;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("状态 （0.待处理、1.处理中、2.待评价、3.已完成）")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createDate;
}
