/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.bms.other.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 树选择
 */
@Data
@ApiModel(value = "树选择")
public class BmsTreeSelectDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类目")
    private String title;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "键")
    private String key;

    @ApiModelProperty(value = "子节点")
    private List children;

}