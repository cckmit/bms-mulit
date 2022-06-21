package cn.amigosoft.modules.area.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cxb
 * @Description:
 * @Date: create in 2020/8/31 23:26
 */
@Data
@ApiModel(value = "楼层房联动查询")
public class AreaBulidDTO {
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "area_id")
    private Long areaId;

    @ApiModelProperty(value = "名称")
    private String name;
}
