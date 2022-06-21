package cn.amigosoft.modules.assets.dto;

import cn.amigosoft.common.utils.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: xb
 * @Description:
 * @Date: create in 2021/6/1 20:27
 */
@Data
@ApiModel("类型树形结构")
public class AssetsEquipmentTypeTreeDTO extends TreeNode {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty(value = "父级ID")
    private Long pid;

    @ApiModelProperty("类别编号")
    private String typeNo;

    @ApiModelProperty(value = "类别名称")
    private String name;

    @ApiModelProperty("类别层级")
    private Integer level;

    @ApiModelProperty("备注")
    private String remark;
}
