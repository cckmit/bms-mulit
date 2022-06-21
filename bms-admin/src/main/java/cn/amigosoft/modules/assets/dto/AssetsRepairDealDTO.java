package cn.amigosoft.modules.assets.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
@ApiModel(value="AssetsRepairDeal对象", description="资产维修处理表")
@Data
public class AssetsRepairDealDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("租户ID")
    private Long tenantId;

    @ApiModelProperty("维修表ID(iotsaas_assets_repair)")
    private Long assetsRepairId;

    @ApiModelProperty("被转派用户ID(iotsaas_person)")
    private Long toPersonId;

    @ApiModelProperty("处理状态 （0.待处理、1.处理中、2.待评价、3.已完成）")
    private Integer status;

    @ApiModelProperty("处理结果")
    private String result;

    @ApiModelProperty("处理图片")
    private String imgs;

    @ApiModelProperty("是否派单(0:直接处理 1:指派他人)")
    private Integer isDispatch;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建者")
    private Long creator;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("更新者")
    private Long updater;

    @ApiModelProperty("更新时间")
    private Date updateDate;

}
