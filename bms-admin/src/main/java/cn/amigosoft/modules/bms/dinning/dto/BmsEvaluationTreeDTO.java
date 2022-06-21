package cn.amigosoft.modules.bms.dinning.dto;

import cn.amigosoft.common.utils.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 评价统计
 */
@Data
@ApiModel(value = "评价统计 ")
public class BmsEvaluationTreeDTO extends TreeNode<BmsEvaluationTreeDTO> implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "父级ID")
    private Long pid;

    @ApiModelProperty(value = "评分情况")
    private Integer count;

}