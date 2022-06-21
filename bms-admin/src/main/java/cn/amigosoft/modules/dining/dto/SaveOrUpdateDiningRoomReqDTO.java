package cn.amigosoft.modules.dining.dto;

import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 智慧餐厅 实体类
 * </p>
 *
 * @author : xuziming
 * @version : 1.0
 * @date : 2021-04-28 19:23:16
 */
@ApiModel(value = "保存餐厅请求参数")
@Data
public class SaveOrUpdateDiningRoomReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "员工id")
    private Long personStaffId;

    @ApiModelProperty(value = "0 正常 1暂停")
    private Integer status = 0;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "排序")
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer sort;

    @ApiModelProperty(value = "备注")
    @Size(max = 100, message = "备注最长为80个字")
    private String remark;

    @ApiModelProperty("餐厅名称")
    @NotBlank(message = "餐厅名称不能为空", groups = DefaultGroup.class)
    private String name;

    @ApiModelProperty("常规座位数")
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer seats;

    @ApiModelProperty("最大接待量")
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer maxCapacity;

    @ApiModelProperty("楼层区域id")
//    @NotNull(message = "区域id不能为空", groups = DefaultGroup.class)
    private Long areaId;
}
