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
public class AssetsRepairDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("报修资产编码(iotsaas_assets_equipment)")
    private String assetsEquipmentCode;

    @ApiModelProperty("资产类别")
    private String assetsEquipmentType;

    @ApiModelProperty("资产名称")
    private String assetsEquipmentName;

    @ApiModelProperty("所在区域Id")
    private Long areaId;

    @ApiModelProperty("所在区域")
    private String areaName;
    
    @ApiModelProperty("报修人")
    private Long creator;

    @ApiModelProperty("报修人")
    private String creatorName;

    @ApiModelProperty("报修人手机")
    private String phone;

    @ApiModelProperty("报修图片")
    private String imgs;

    @ApiModelProperty("报修内容")
    private String content;

    @ApiModelProperty("是否直接处理")
    private Integer isDispatch;

    @ApiModelProperty("处理人Id")
    private Long dealPersonId;

    @ApiModelProperty("处理人名称")
    private String dealPersonName;

    @ApiModelProperty("处理人电话")
    private String dealPerosnPhone;

    @ApiModelProperty("处理结果")
    private String dealResult;

    @ApiModelProperty("处理时间")
    private Date dealDate;

    @ApiModelProperty("处理结果图片")
    private String dealImgs;

    @ApiModelProperty("评价评分")
    private Integer grade;

    @ApiModelProperty("评价描述")
    private String gradeDesc;

    @ApiModelProperty("评价图片")
    private String gradeImgs;

    @ApiModelProperty("状态 （0.待处理、1.处理中、2.待评价、3.已完成）")
    private Integer status;

    @ApiModelProperty("申请时间")
    private Date createDate;

    @ApiModelProperty("取消时间")
    private Date updateDate;
}
