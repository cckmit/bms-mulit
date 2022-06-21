package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xuziming
 * @date 2021/4/28 20:18
 * description：
 */
@Data
@ApiModel("餐厅分页返回信息")
public class DiningRoomPageRespDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty("餐厅名称")
    private String name;

    @ApiModelProperty("常规座位数")
    private Integer seats;

    @ApiModelProperty("最大接待量")
    private Integer maxCapacity;

    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "管理员名称")
    private String manager;

    @ApiModelProperty(value = "区域类型")
    private Long areaFloorId;
}