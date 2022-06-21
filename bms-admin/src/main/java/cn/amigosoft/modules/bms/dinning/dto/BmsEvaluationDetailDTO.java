package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 评价详情
 */
@Data
@ApiModel(value = "资产表 ")
public class BmsEvaluationDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "选项")
	private List<Map<String,String>> options;

	@ApiModelProperty(value = "选择")
	private String checkedValue;

	@ApiModelProperty(value = "评价")
	private String evaluation;

}