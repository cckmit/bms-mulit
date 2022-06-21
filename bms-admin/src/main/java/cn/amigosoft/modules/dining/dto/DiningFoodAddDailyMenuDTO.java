package cn.amigosoft.modules.dining.dto;

import cn.amigosoft.common.validator.group.DefaultGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
public class DiningFoodAddDailyMenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜品库 iotsaas_dining_food_lib.id")
    @NotNull(message = "ids不能为空",groups = DefaultGroup.class)
    private Long[] ids;

    @ApiModelProperty("每日菜品日期 yyyy-mm-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "菜品日期不能为空",groups = DefaultGroup.class)
    private Date menuDate;

    @ApiModelProperty("每日菜品时段 1.早餐 2.午餐 3.晚餐")
    @NotNull(message = "菜品时段不能为空",groups = DefaultGroup.class)
    private Integer diningType;


}
