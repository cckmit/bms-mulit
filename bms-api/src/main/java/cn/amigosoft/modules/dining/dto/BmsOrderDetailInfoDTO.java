package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 订单信息表
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-09-15
 */
@Data
@ApiModel
public class BmsOrderDetailInfoDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "用餐日期")
    private String eatDate;

    @ApiModelProperty(value = "订单列表")
    private List<BmsOrderDetailDTO> orderDetailList;
}
