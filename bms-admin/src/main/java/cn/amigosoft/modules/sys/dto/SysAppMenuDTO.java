package cn.amigosoft.modules.sys.dto;

import cn.amigosoft.common.utils.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 小程序菜单表
 */
@Data
@ApiModel(value = "小程序菜单表 ")
public class SysAppMenuDTO extends TreeNode<SysAppMenuDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "组件地址")
    private String url;

    @ApiModelProperty(value = "上级ID，一级菜单为0")
    private Long pid;

    @ApiModelProperty(value = "授权")
    private String permission;

    @ApiModelProperty(value = "类型   0：菜单   1：按钮")
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "删除标识 （0：未删除；1：已删除）")
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