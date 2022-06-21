package cn.amigosoft.modules.dining.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class DiningFoodCommentPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    private Long id;

    @ApiModelProperty("菜品编号")
    private String libNo;

    @ApiModelProperty("餐厅名称")
    private String diningRoomName;

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("每日菜品日期 yyyy-mm-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date menuDate;

    @ApiModelProperty("每日菜品日期 yyyy-mm-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private String menuDateStr;

    @ApiModelProperty("每日菜品时段 1.早餐 2.午餐 3.晚餐")
    private Integer diningType;

    @ApiModelProperty("每日菜品时段 1.早餐 2.午餐 3.晚餐")
    private String diningTypeStr;

    @ApiModelProperty("总评论数")
    private Integer commentTotal;

    @ApiModelProperty("1星评价百分比")
    private String percentageByOne;

    @ApiModelProperty("2星评价百分比")
    private String percentageByTwo;

    @ApiModelProperty("3星评价百分比")
    private String percentageByThree;

    @ApiModelProperty("4星评价百分比")
    private String percentageByFour;

    @ApiModelProperty("5星评价百分比")
    private String percentageByFive;

    @ApiModelProperty("1星评价数量")
    private Integer countByOne;

    @ApiModelProperty("2星评价数量")
    private Integer countByTwo;

    @ApiModelProperty("3星评价数量")
    private Integer countByThree;

    @ApiModelProperty("4星评价数量")
    private Integer countByFour;

    @ApiModelProperty("5星评价数量")
    private Integer countByFive;



}
