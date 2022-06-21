package cn.amigosoft.modules.home.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MapDTO {

    @ApiModelProperty(value = "值名称")
    private String valueName;

    @ApiModelProperty(value = "值数据")
    private Integer value;

}
