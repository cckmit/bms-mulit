package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单信息表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Data
@ApiModel
public class BmsEatDateDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "食用日期")
    private String eatDate;

    @ApiModelProperty(value = "周几")
    private String dayOfWeek;

    @ApiModelProperty(value = "是否被选中")
    private Boolean checked;

}
