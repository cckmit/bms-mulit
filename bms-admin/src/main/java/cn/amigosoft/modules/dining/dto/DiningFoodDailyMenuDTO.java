package cn.amigosoft.modules.dining.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class DiningFoodDailyMenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    private Long id;

    @ApiModelProperty("租户Id")
    private Long tenantId;

    @ApiModelProperty("每日菜品日期 yyyy-mm-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date menuDate;

    @ApiModelProperty("菜品Id iotsaas_dining_foods_lib.id")
    private Long diningFoodLibId;

    @ApiModelProperty("每日菜品时段 1.早餐 2.午餐 3.晚餐")
    private Integer diningType;

    @ApiModelProperty("是否上架 0:否 1:是")
    private Integer status;

    @ApiModelProperty("创建者id")
    private Long creator;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("修改者 id")
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;

    @ApiModelProperty("修改日期")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

}
