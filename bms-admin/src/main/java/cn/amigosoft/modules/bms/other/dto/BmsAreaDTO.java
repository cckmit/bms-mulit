package cn.amigosoft.modules.bms.other.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 区域表 
 */
@Data
@ApiModel(value = "区域表 ")
public class BmsAreaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "编码")
	private String code;

	@ApiModelProperty(value = "排序")
	private Integer sort;

	@ApiModelProperty(value = "上一级ID")
	private Long pid;

	private List<BmsAreaDTO> children;

	@ApiModelProperty(value = "标签名称")
	private String label;

	@ApiModelProperty(value = "标签值")
	private String value;
}