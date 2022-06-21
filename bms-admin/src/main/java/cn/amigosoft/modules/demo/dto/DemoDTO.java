/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.demo.dto;

import cn.amigosoft.common.validator.group.AddGroup;
import cn.amigosoft.common.validator.group.DefaultGroup;
import cn.amigosoft.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 新闻管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "新闻管理")
public class DemoDTO implements Serializable {

    @ApiModelProperty(value = "id")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "{news.title.require}", groups = DefaultGroup.class)
    private String title;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "{news.content.require}", groups = DefaultGroup.class)
    private String content;

    @ApiModelProperty(value = "发布时间")
    private Date pubDate;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

}
