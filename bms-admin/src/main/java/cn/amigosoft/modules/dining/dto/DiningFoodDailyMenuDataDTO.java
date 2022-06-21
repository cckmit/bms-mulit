package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 每日菜品 实体类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 14:45:58
 */
@ApiModel(value="DiningFoodDailyMenu对象", description="每日菜品")
@Data
public class DiningFoodDailyMenuDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("早餐")
    List<DiningFoodDailyMenuListDTO> breakfast;

    @ApiModelProperty("午餐")
    List<DiningFoodDailyMenuListDTO> lunch;

    @ApiModelProperty("晚餐")
    List<DiningFoodDailyMenuListDTO> dinner;

}
