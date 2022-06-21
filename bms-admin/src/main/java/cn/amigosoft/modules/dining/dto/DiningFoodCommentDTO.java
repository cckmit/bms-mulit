package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 每日菜品评价 实体类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 15:11:09
 */
@ApiModel(value="DiningFoodComment对象", description="每日菜品评价")
@Data
public class DiningFoodCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论id")
    private Long id;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @ApiModelProperty("每日菜品id( iotsaas_dining_food_daily_menu.id)")
    private Long diningFoodDailyMenuId;

    @ApiModelProperty("菜品评分 值为（1-5）")
    private Integer score;

    @ApiModelProperty("评价人id sys_user.id")
    private Long reviewerUserId;

    @ApiModelProperty("评价内容")
    private String content;

    @ApiModelProperty("评价时间")
    private Date commentDate;

    @ApiModelProperty("创建者id")
    private Long creator;

    @ApiModelProperty("创建日期")
    private Date createDate;

}
