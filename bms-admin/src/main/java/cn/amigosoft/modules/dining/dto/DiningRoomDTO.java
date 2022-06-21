package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 智慧餐厅 实体类
 * </p>
 *
 * @author : xuziming
 * @version : 1.0
 * @date : 2021-04-28 19:23:16
 */
@ApiModel(value = "DiningRoom对象", description = "智慧餐厅")
@Data
public class DiningRoomDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("租户id(iotsaas_tenant.id)")
    private Long tenantId;

    @ApiModelProperty(value = "员工id")
    private Long personStaffId;

    @ApiModelProperty("餐厅名称")
    private String name;

    @ApiModelProperty("常规座位数")
    private Integer seats;

    @ApiModelProperty("最大接待量")
    private Integer maxCapacity;

    @ApiModelProperty("房间所属区域id iotsaas_area.id")
    private Long areaId;

    @ApiModelProperty("房间所属楼栋id iotsaas_area_building.id")
    private Long areaBuildingId;

    @ApiModelProperty("房间所属楼层id iotsaas_area_floor.id")
    private Long areaFloorId;

    @ApiModelProperty(value = "0 正常 1暂停")
    private Integer status;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty("更新者")
    private Long updater;

    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty("创建者")
    private Long creator;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty(value = "管理员")
    private String manager;

}
