package cn.amigosoft.modules.assets.dto;

import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资产设备分类表  实体类
 * </p>
 *
 * @author : cxb
 * @version : 1.0
 * @date : 2021-05-31 15:29:44
 */
@ApiModel(value = "AssetsEquipmentType对象", description = "资产设备分类表 ")
@Data
public class AssetsEquipmentTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty("租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "父级ID", required = true)
    @NotNull(message = "请选择上级节点", groups = DefaultGroup.class)
    private Long pid;

    @ApiModelProperty("上级ID，逗号分隔")
    private String pids;

    @ApiModelProperty(value = "类别编号", required = true)
    @NotBlank(message = "类别编号不能为空", groups = UpdateGroup.class)
    private String typeNo;

    @ApiModelProperty(value = "类别名称", required = true)
    @Length(max = 128, message = "类别名称过大", groups = DefaultGroup.class)
    @NotBlank(message = "类别名称不能为空", groups = DefaultGroup.class)
    private String name;

    @ApiModelProperty(value = "类别层级", required = true)
    @NotNull(message = "当前级别不能为空", groups = UpdateGroup.class)
    private Integer level;

    @ApiModelProperty("备注")
    @Length(max = 512, message = "备注过大", groups = DefaultGroup.class)
    private String remark;

    @ApiModelProperty("是否删除: 1、已删除  0、未删除")
    private Integer del;

    @ApiModelProperty("创建者")
    private Long creator;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("更新者")
    private Long updater;

    @ApiModelProperty("更新时间")
    private Date updateDate;
}
