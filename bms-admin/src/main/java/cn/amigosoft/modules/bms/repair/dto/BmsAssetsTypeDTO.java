package cn.amigosoft.modules.bms.repair.dto;

import cn.amigosoft.common.utils.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 资产类别表
 */
@Data
@ApiModel(value = "资产类别表 ")
public class BmsAssetsTypeDTO extends TreeNode<BmsAssetsTypeDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "父级ID")
    private Long pid;

    @ApiModelProperty(value = "上级ID（逗号分隔）")
    private String pids;

    @ApiModelProperty(value = "类别编号")
    private String code;

    @ApiModelProperty(value = "类别名称")
    private String name;

    @ApiModelProperty(value = "类别层级 （从1开始）")
    private Integer level;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "删除标识 （0：未删除 1：已删除）")
    private Integer del;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新者")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

}