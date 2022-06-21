package cn.amigosoft.modules.dining.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜品库表 实体类
 * </p>
 *
 * @author : hupihshi
 * @version : 1.0
 * @date : 2021-04-20 14:31:04
 */
@ApiModel(value="DiningFoodLib对象", description="菜品库表")
@Data
public class DiningFoodLibDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜品id")
    private Long id;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @ApiModelProperty("创建者Id")
    private Long creator;

    @ApiModelProperty("菜品编号")
    private String libNo;

    @ApiModelProperty("餐厅编号")
    @NotNull(message = "餐厅名称不能为空")
    private Long diningRoomId;

    @ApiModelProperty("菜品名称")
    @NotNull(message = "菜品名称不能为空")
    @Length( max = 15, message = "菜品名称长度不能超过15")
    private String name;

    @ApiModelProperty("菜品类型（1.热菜 2.凉菜 3.面点 4.面食 5.杂粮 6.主食 7.小吃 8.其他 9.外卖预约）")
    private Integer type;

    @ApiModelProperty("菜品金额")
    private Double price;

    @ApiModelProperty("菜品描述")
    private String remark;

    @ApiModelProperty("菜品图片 限1张")
    private String imageUrl;

    @ApiModelProperty("是否长期（1.是 2.否）")
//    @NotNull(message = "是否长期不能为空")
    private Integer isEnduring;

    @ApiModelProperty("早午饭餐（当类型为长期菜品时可多选,以逗号分隔：1.早餐 2.午餐 3.晚餐）")
    private  String diningType;

    @ApiModelProperty("周期几 当周期为长期菜品时可选：([1-7]周一-周日,以逗号分割)")
    private String week;

    @ApiModelProperty("修改者 id")
    private Long updater;

    @ApiModelProperty("修改日期")
    private Date updateDate;

    @ApiModelProperty("是否删除 状态")
    private Integer status;
}
