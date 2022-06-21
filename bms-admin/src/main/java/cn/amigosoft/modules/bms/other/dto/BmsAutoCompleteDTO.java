/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.bms.other.dto;

import cn.amigosoft.modules.sys.dto.SysUserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 自动完成
 */
@Data
@ApiModel(value = "自动完成")
public class BmsAutoCompleteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类目")
    private String title;

    @ApiModelProperty(value = "子节点")
    private List<SysUserDTO> children;

}